package uz.easycongroup.smartenergy.presentation.support.moxy

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import moxy.MvpAppCompatFragment
import uz.easycongroup.smartenergy.BuildConfig
import uz.easycongroup.smartenergy.presentation.presentation.common.features.progressdialog.ProgressDialogFragment
import uz.easycongroup.smartenergy.BuildConfig.FILE_PROVIDER
import java.io.*

internal abstract class BaseMvpPhotoFragment(@LayoutRes contentLayoutId: Int) :
    MvpAppCompatFragment(contentLayoutId) {

    protected var progressDialog: ProgressDialogFragment? = null

    abstract fun setImageUriFromCamera(uri: Uri)

    abstract fun setImageUriFromGallery(uri: Uri)

    open fun onCameraPermissionDeniedScreen(isRequiredOpenAppSettings: Boolean) {
        openAppSettingsScreen()
    }

    open fun onGalleryPermissionDeniedScreen(isRequiredOpenAppSettings: Boolean) {
        openAppSettingsScreen()
    }

    fun checkPermissionAndTakePhoto() {
        val permission = Manifest.permission.CAMERA
        when {
            checkSelfPermission(requireContext(), permission) == PERMISSION_GRANTED -> {
                openCameraForTakingPhoto()
            }
            shouldShowRequestPermissionRationale(permission) -> {
                onCameraPermissionDeniedScreen(true)
            }
            else -> {
                requestCameraPermissionLauncher.launch(permission)
            }
        }
    }

    fun checkPermissionAndChooseImage() {
        val permission = Manifest.permission.READ_EXTERNAL_STORAGE
        when (val grant = checkSelfPermission(requireContext(), permission) == PERMISSION_GRANTED) {
            grant -> openGalleryForChoosingImage()
            shouldShowRequestPermissionRationale(permission) -> onGalleryPermissionDeniedScreen(true)
            else -> requestGalleryPermissionLauncher.launch(permission)
        }
    }

    fun openAppSettingsScreen() {
        startActivity(Intent().apply {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            data = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null)
        })
    }

    protected fun showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = ProgressDialogFragment.newInstance()
        }
        progressDialog?.showProgressDialog(childFragmentManager)
    }

    protected fun hideProgressDialog() {
        progressDialog?.hideProgressDialog()
    }

    private fun openCameraForTakingPhoto() {
        lifecycleScope.launchWhenStarted {
            createTemporaryFileUri("tmp_taken_image").let { uri ->
                temporaryFileForTakenPhoto = uri
                takeImageResult.launch(uri)
            }
        }
    }

    private fun openGalleryForChoosingImage() {
        selectImageFromGalleryResult.launch("image/*")
    }

    private var temporaryFileForTakenPhoto: Uri? = null

    private val takeImageResult = registerForActivityResult(TakePicture()) { isSuccess ->
        if (isSuccess) {
            temporaryFileForTakenPhoto?.let { takenImageUri ->
                val compressedImageUri = compressImage(takenImageUri)

                setImageUriFromCamera(compressedImageUri)
            }
        }
    }

    private val selectImageFromGalleryResult =
        registerForActivityResult(GetContent()) { uri: Uri? ->
            uri?.let { galleryPhotoUri ->

                lifecycleScope.launchWhenStarted {
                    val copiedImageUri = copyImageFromGalleryToCacheDirectory(galleryPhotoUri)
                    val compressedImageUri = compressImage(copiedImageUri)

                    setImageUriFromGallery(compressedImageUri)
                }
            }
        }

    private val requestCameraPermissionLauncher =
        registerForActivityResult(RequestPermission()) { isGranted: Boolean ->
            if (isGranted) openCameraForTakingPhoto()
            else onGalleryPermissionDeniedScreen(false)
        }


    private val requestGalleryPermissionLauncher =
        registerForActivityResult(RequestPermission()) { isGranted: Boolean ->
            if (isGranted) openGalleryForChoosingImage()
            else onGalleryPermissionDeniedScreen(false)
        }

    private fun createTemporaryFile(fileName: String = "tmp_image_file_"): File {
        val tmpFile = File.createTempFile(fileName, ".png", cacheDir)
            .apply {
                createNewFile()
                deleteOnExit()
            }

        return tmpFile
    }

    private fun createTemporaryFileUri(fileName: String): Uri {
        val tmpFile = createTemporaryFile(fileName)

        return getUriForFile(tmpFile)
    }

    private fun getUriForFile(file: File): Uri {
        return FileProvider.getUriForFile(applicationContext, FILE_PROVIDER, file)
    }


    private fun copyImageFromGalleryToCacheDirectory(uri: Uri): Uri {
        val tmpFile = createTemporaryFile("tmp_chosen_image_")

        runCatching {
            val inputStream: InputStream = applicationContext.contentResolver.openInputStream(uri)!!
            val fileOutputStream = FileOutputStream(tmpFile)

            val buffer = ByteArray(1024)
            var bytesRead: Int
            while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                fileOutputStream.write(buffer, 0, bytesRead)
            }

            fileOutputStream.close()
            inputStream.close()
        }

        return getUriForFile(tmpFile)
    }

    private fun compressImage(uri: Uri): Uri {

        val originalFile = File(applicationContext.cacheDir, "/${uri.lastPathSegment}")

        val compressedFile: File = createTemporaryFile("${uri.lastPathSegment}_compressed")

        val outputStream: OutputStream = FileOutputStream(compressedFile)
        var bitmap = BitmapFactory.decodeFile(originalFile.path)

        val imageRotation: Int = getImageRotation(originalFile)
        if (imageRotation != 0) bitmap = getBitmapRotatedByDegree(bitmap, imageRotation)

        bitmap.compress(CompressFormat.JPEG, 30, outputStream)

        outputStream.flush()
        outputStream.close()

        return getUriForFile(compressedFile)
    }

    private fun getImageRotation(imageFile: File): Int {
        var exif: ExifInterface? = null
        var exifRotation = 0
        try {
            exif = ExifInterface(imageFile.path)
            exifRotation = exif.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL
            )
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return if (exif == null) 0 else exifToDegrees(exifRotation)
    }

    private fun exifToDegrees(rotation: Int): Int {
        if (rotation == ExifInterface.ORIENTATION_ROTATE_90) return 90 else if (rotation == ExifInterface.ORIENTATION_ROTATE_180) return 180 else if (rotation == ExifInterface.ORIENTATION_ROTATE_270) return 270
        return 0
    }

    private fun getBitmapRotatedByDegree(bitmap: Bitmap, rotationDegree: Int): Bitmap? {
        val matrix = Matrix()
        matrix.preRotate(rotationDegree.toFloat())
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }

    private val cacheDir
        get() = requireContext().cacheDir

    private val applicationContext
        get() = requireContext().applicationContext
}