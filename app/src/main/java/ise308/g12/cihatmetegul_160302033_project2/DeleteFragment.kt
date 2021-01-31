package ise308.g12.cihatmetegul_160302033_project2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import ise308.g12.cihatmetegul_160302033_project2.db.DataManager

class DeleteFragment(deleteFirstName: String, deleteLastName: String) : Fragment(),
    View.OnClickListener {
    val deleteFName = deleteFirstName
    val deleteLName = deleteLastName
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.delete_fragment, container, false)
        val btn: Button = view.findViewById(R.id.button_selected_delete)
        val btn2: Button = view.findViewById(R.id.button_go_back)
        btn.setOnClickListener(this)
        btn2.setOnClickListener(this)
        return view
        // Inflate the layout for this fragment
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_selected_delete -> {

                val dm = DataManager(activity!!)
                dm.remove(deleteLName)
                requireActivity().run {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }

            }
            R.id.button_go_back -> {

                requireActivity().run {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }

        }
    }

}


