package ise308.g12.cihatmetegul_160302033_project2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import ise308.g12.cihatmetegul_160302033_project2.db.DataManager

class EditFragment(
    e_firstName: String,
    e_lastName: String,
    e_position: String,
    e_age: String
) : Fragment(),
    View.OnClickListener {
    val eFirstName = e_firstName
    val eLastName = e_lastName
    val ePosition = e_position
    val eAge = e_age
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.edit_fragment, container, false)
        val btns: Button = view.findViewById(R.id.button_edit)
        val btns2: Button = view.findViewById(R.id.button_go_back2)
        btns.setOnClickListener(this)
        btns2.setOnClickListener(this)
        return view
        // Inflate the layout for this fragment
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_edit -> {
                val dm = DataManager(activity!!)



                val nInj = view?.findViewById<CheckBox>(R.id.change_checkBox)

                dm.remove(eLastName)
                if (nInj?.isChecked == true){

                    dm.insert(eLastName, eFirstName, ePosition, eAge, true)
                }
                if (nInj?.isChecked == false){

                    dm.insert(eLastName, eFirstName, ePosition, eAge, false)
                }


                requireActivity().run {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }

            }
            R.id.button_go_back2 -> {

                requireActivity().run {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }

        }
    }

}


