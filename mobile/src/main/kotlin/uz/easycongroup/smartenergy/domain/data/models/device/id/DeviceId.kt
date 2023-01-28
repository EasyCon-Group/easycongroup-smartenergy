package uz.easycongroup.smartenergy.domain.data.models.device.id

interface DeviceId {

    fun getSerialBasedDeviceId(): String

    fun getTimeBasedDeviceId(): String
}