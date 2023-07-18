package ru.com.bulat.navcomponent

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.com.bulat.navcomponent.databinding.FragmentBoxBinding
import kotlin.random.Random

class BoxFragment : Fragment(R.layout.fragment_box) {

    lateinit var binding : FragmentBoxBinding
    private val args : BoxFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentBoxBinding.bind(view)

        //1-q вариант
        //val color = BoxFragmentArgs.fromBundle(requireArguments()).color

        //2-й вариант
        val color = args.color

        binding.root.setBackgroundColor(color)

        binding.goBackButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.generatedNumberButton.setOnClickListener {
            val number = Random.nextInt(100)
            //parentFragmentManager.setFragmentResult(REQUEST_CODE, bundleOf(EXTRA_RANDOM_NUMBER to number))
            findNavController().previousBackStackEntry?.savedStateHandle?.set(EXTRA_RANDOM_NUMBER, number)
            findNavController().popBackStack()
        }

        binding.openSecretButton.setOnClickListener {
            findNavController().navigate(BoxFragmentDirections.actionBoxFragmentToSecretFragment())
        }
    }

    companion object {
        const val EXTRA_RANDOM_NUMBER = "EXTRA_RANDOM_NUMBER"

    }
}