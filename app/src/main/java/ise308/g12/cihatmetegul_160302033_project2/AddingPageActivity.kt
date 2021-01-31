package ise308.g12.cihatmetegul_160302033_project2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import ise308.g12.cihatmetegul_160302033_project2.db.DataManager

class AddingPageActivity : AppCompatActivity() {
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding_page)


        val dm = DataManager(this)

        val btnAdd = findViewById<Button>(R.id.button_add_player)
        val newFirstName = findViewById<EditText>(R.id.change_first_name)
        val newLastName = findViewById<EditText>(R.id.change_last_name)
        val newPosition = findViewById<EditText>(R.id.change_position)
        val newAge = findViewById<EditText>(R.id.change_age)
        val newInjSit = findViewById<CheckBox>(R.id.change_checkBox)


        btnAdd.setOnClickListener {
            if (newLastName.text.toString() != "") {
                if (newFirstName.text.toString() != "") {

                    if (newPosition.text.toString() != "") {
                        if (newAge.text.toString() != "") {

                            dm.insert(
                                newLastName.text.toString(),
                                newFirstName.text.toString(),
                                newPosition.text.toString(),
                                newAge.text.toString(),
                                newInjSit.isChecked
                            )
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Please fill all lines.", Toast.LENGTH_SHORT).show()
            }


        }


    }


}