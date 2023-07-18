package ru.com.bulat.navcomponent

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import ru.com.bulat.navcomponent.databinding.FragmentRootBinding

class RootFragment : Fragment(R.layout.fragment_root) {

    private lateinit var binding : FragmentRootBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRootBinding.bind(view)

        binding.openGreenBoxButton.setOnClickListener {
            openBox(Color.rgb(200,255,200), "Green")
        }

        binding.openYellowBoxButton.setOnClickListener {
            openBox(Color.rgb(255,255,200), "Yellow")
        }

        val liveData =  findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Int>(BoxFragment.EXTRA_RANDOM_NUMBER)
        liveData?.observe(viewLifecycleOwner){number ->
            if (number != null) {
                Toast.makeText(requireContext(), "Generated number: $number", Toast.LENGTH_SHORT)
                    .show()
                liveData.value = null
            }
        }

//        parentFragmentManager.setFragmentResultListener(BoxFragment.REQUEST_CODE, viewLifecycleOwner) { _, data ->
//            val number = data.getInt(BoxFragment.EXTRA_RANDOM_NUMBER)
//            Toast.makeText(requireContext(), "Generated number: $number", Toast.LENGTH_SHORT).show()
//        }
    }

    private fun openBox(color:Int, colorName : String){

        val direction = RootFragmentDirections.actionRootFragmentToBoxFragment(
            colorName,
            color,
        )

        findNavController().navigate(
            direction,
            navOptions {
                anim {
                    enter = R.anim.enter
                    exit = R.anim.exit
                    popEnter = R.anim.pop_enter
                    popExit = R.anim.pop_exit
                }
            }
        )
    }
}