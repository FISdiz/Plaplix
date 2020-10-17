package cl.desafiolatam.plaplixtest.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import cl.desafiolatam.plaplix.R
import cl.desafiolatam.plaplix.model.pojo.Phone
import cl.desafiolatam.plaplix.viewmodel.PhoneViewModel
import kotlinx.android.synthetic.main.fragment_phone_list.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PhoneListFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var phoneSmallRecycler = ArrayList<Phone>()
    private lateinit var adapter : PhoneAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_phone_list, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PhoneListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter =
            PhoneAdapter(phoneSmallRecycler)
        recycler_phone.adapter = adapter

        val model : PhoneViewModel by activityViewModels()
        model.phoneSmallList.observe(viewLifecycleOwner, Observer {
            adapter.updateItems(it)
        })
    }
}