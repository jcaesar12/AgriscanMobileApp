package com.gpartners.agriscanmobileapplication.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gpartners.agriscanmobileapplication.databinding.FragmentOnboardingBinding
import com.gpartners.agriscanmobileapplication.ui.mock_data.OnboardingItem

class OnboardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val ARG_TITLE = "title"
        private const val ARG_DESC = "desc"
        private const val ARG_IMAGE = "image"

        fun newInstance(item: OnboardingItem): OnboardingFragment {
            val fragment = OnboardingFragment()
            val bundle = Bundle().apply {
                putString(ARG_TITLE, item.title)
                putString(ARG_DESC, item.description)
                putInt(ARG_IMAGE, item.imageRes)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.onboardingTitle.text = arguments?.getString(ARG_TITLE)
        binding.onboardingDescription.text = arguments?.getString(ARG_DESC)
        binding.onboardingImage.setImageResource(arguments?.getInt(ARG_IMAGE) ?: 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}