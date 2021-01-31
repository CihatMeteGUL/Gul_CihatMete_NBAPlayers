package ise308.g12.cihatmetegul_160302033_project2

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ise308.g12.cihatmetegul_160302033_project2.db.DataManager
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var playersList: ArrayList<Players>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializePlayers()

        val recyclerViewPlayers = findViewById<RecyclerView>(R.id.recyclerView)


        val playersAdapter = PlayersAdapter(playersList, this)


        val layoutManager = LinearLayoutManager(this)
        recyclerViewPlayers.layoutManager = layoutManager
        recyclerViewPlayers.itemAnimator = DefaultItemAnimator()
        recyclerViewPlayers.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
        recyclerViewPlayers.adapter = playersAdapter


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id = item.getItemId()

        if (id == R.id.add_item_icon) {
            val intent = Intent(this, AddingPageActivity::class.java)
            startActivity(intent)
            return true
        }


        return super.onOptionsItemSelected(item)
    }


    private fun initializePlayers() {
        var i = 0

        val dm = DataManager(this)

        playersList = ArrayList<Players>()

//delete yapınca dm count problemi, injured eklemek lazım olabilir
        while (i < dm.searchAll().size) {

            playersList.add(
                Players(
                    dm.searchAll()[i].firstName.toString(),
                    dm.searchAll()[i].lastName.toString(),
                    dm.searchAll()[i].age,
                    dm.searchAll()[i].positionPlayed.toString(),
                    dm.searchAll()[i].isInjured
                )
            )
            i++
        }

    }


    fun openEditFragment(
        e_firstName: String,
        e_lastName: String,
        e_position: String,
        e_age: String
    ) {
        var myFragment = EditFragment(e_firstName, e_lastName, e_position, e_age)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.act_main, myFragment)
        fragmentTransaction.commit()

    }

    fun openDeleteFragment(
        deleteFirstName: String,
        deleteLastName: String,
    ) {
        var myFragment = DeleteFragment(deleteFirstName, deleteLastName)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.act_main, myFragment)
        fragmentTransaction.commit()

    }
    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }



}
