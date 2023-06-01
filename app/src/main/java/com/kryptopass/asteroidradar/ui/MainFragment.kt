package com.kryptopass.asteroidradar.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kryptopass.asteroidradar.R
import com.kryptopass.asteroidradar.databinding.FragmentMainBinding
import com.kryptopass.asteroidradar.domain.Asteroid

class MainFragment : Fragment(), MenuProvider {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentMainBinding.inflate(inflater)
        with(binding) {
            lifecycleOwner = this@MainFragment
            mainViewModel = viewModel
            asteroidRecycler.adapter =
                AsteroidRecyclerAdapter(AsteroidRecyclerAdapter.OnClickListener { asteroid: Asteroid ->
                    viewModel.displayAsteroidDetails(asteroid)
                })
        }

        viewModel.navigateToSelectedAsteroid.observe(viewLifecycleOwner) { asteroid: Asteroid? ->
            if (asteroid != null) {
                this.findNavController().navigate(MainFragmentDirections.actionShowDetail(asteroid))
                viewModel.displayAsteroidDetailsComplete()
            }
        }
        viewModel.filter.observe(viewLifecycleOwner) {
            viewModel.updateFilter()
        }

        activity?.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.main_overflow_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        viewModel.setFilter(
            when (menuItem.itemId) {
                R.id.show_week_menu -> NeoApiFilter.WEEK
                R.id.show_today_menu -> NeoApiFilter.DAY
                else -> NeoApiFilter.SAVED
            }
        )

        return true
    }
}
