package com.andrukh.booking.screens.hotelRoom

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.andrukh.booking.R
import com.andrukh.booking.database.BookingDatabase
import com.andrukh.booking.databinding.FragmentHotelRoomBinding

/**
 * A fragment representing a list of Items.
 */
class RoomFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentHotelRoomBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_hotel_room, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = BookingDatabase.getInstance(application).roomDAO

        val viewModelFactory = RoomViewModelFactory(dataSource, application)

        val roomViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(RoomViewModel::class.java)

        binding.roomViewModel = roomViewModel

        // binding.setLifecycleOwner(this)
        binding.lifecycleOwner = this

        val adapter = HotelRoomRecyclerViewAdapter()
        binding.roomList.adapter = adapter

        roomViewModel.rooms.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            RoomFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}