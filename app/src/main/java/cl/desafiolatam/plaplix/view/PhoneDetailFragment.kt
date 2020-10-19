package cl.desafiolatam.plaplix.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import cl.desafiolatam.plaplix.R
import cl.desafiolatam.plaplix.viewmodel.PhoneViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_phone_detail.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PhoneDetailFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

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
        return inflater.inflate(R.layout.fragment_phone_detail, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PhoneDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val phoneViewModel : PhoneViewModel by activityViewModels()
        phoneViewModel.getDetailFrom(param1!!).observe(viewLifecycleOwner, Observer {
            text_namedet.text = it.name
            text_descdet.text = it.description
            text_lastdet.text = it.lastPrice.toString()
            text_pricedet.text = it.price.toString()
            Picasso
                    .get()
                    .load(it.image)
                    .into(img_phonedet)
        })
    }
}