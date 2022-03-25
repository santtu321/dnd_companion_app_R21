package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import androidx.fragment.app.Fragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {                  // tässä tehdään fragmentti ja fragmentti näytetään jokaisess aactivityssä ja en tiedä miten
        inflater.inflate(R.menu.menu_main, menu)                                            //nuo kuviot menee niin hyvin tuohon appbaariin, mutta se toimii.
    }                                                                                       // menua voi muokata res/menu/menu_main.xml tiedostossa.
    fun showPopup(view: View){

        val popup = PopupMenu(activity, view)
        popup.inflate(R.menu.popup_menu)



    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_home -> {
                val intent = Intent(activity, RuleActivity::class.java)                     // muuten sama intent kutsu kuin normaalisti, mutta intent olion ensimmäinen parametri ei ole
                startActivity(intent)                                                       // this vaan activity
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MenuFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MenuFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}