package uz.easycongroup.smartenergy.presentation.support.cicerone.navigator

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import ru.terrakok.cicerone.commands.Command

class AnimatedCiceroneNavigator(
    activity: FragmentActivity,
    containerId: Int
) : BaseCiceroneNavigator(activity, containerId) {

    override fun setupFragmentTransaction(
        command: Command,
        currentFragment: Fragment?,
        nextFragment: Fragment?,
        fragmentTransaction: FragmentTransaction
    ) {

//        fragmentTransaction.setCustomAnimations(
//            R.anim.open_to_left,
//            R.anim.close_to_left,
//            R.anim.open_to_right,
//            R.anim.close_to_right
//        )
    }
}