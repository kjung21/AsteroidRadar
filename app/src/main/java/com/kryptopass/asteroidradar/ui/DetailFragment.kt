package com.kryptopass.asteroidradar.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.kryptopass.asteroidradar.R
import com.kryptopass.asteroidradar.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val asteroid = DetailFragmentArgs.fromBundle(requireArguments()).selectedAsteroid
        val binding = FragmentDetailBinding.inflate(inflater)

        with(binding) {
            lifecycleOwner = this@DetailFragment
            asteroidDetail = asteroid
            helpButton.setOnClickListener {
                displayAstronomicalUnitExplanationDialog()
            }
        }

        return binding.root
    }

    private fun displayAstronomicalUnitExplanationDialog() {
        val builder = AlertDialog.Builder(requireActivity())
            .setMessage(getString(R.string.astronomical_unit_explanation))
            .setPositiveButton(android.R.string.ok, null)
        builder.create().show()
    }
}
