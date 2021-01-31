package ise308.g12.cihatmetegul_160302033_project2

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import ise308.g12.cihatmetegul_160302033_project2.db.DataManager


class PlayersAdapter(val playersList: ArrayList<Players>, private val mainActivity: MainActivity) :
    RecyclerView.Adapter<PlayersAdapter.PlayerViewHolder>() {
    inner class PlayerViewHolder(playerItemView: View) : RecyclerView.ViewHolder(playerItemView),
        View.OnClickListener {

        var firstname = playerItemView.findViewById<TextView>(R.id.firstname)
        var lastname = playerItemView.findViewById<TextView>(R.id.lastname)
        var position = playerItemView.findViewById<TextView>(R.id.position)
        var injured = playerItemView.findViewById<TextView>(R.id.change_checkBox)

        var age = playerItemView.findViewById<TextView>(R.id.age)
        var deleteIcon: ImageView = playerItemView.findViewById(R.id.delete_icon)
        var editIcon: ImageView = playerItemView.findViewById(R.id.edit_icon)

        init {
            playerItemView.isClickable
            playerItemView.setOnClickListener(this)


        }

        override fun onClick(v: View?) {



        }


    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PlayerViewHolder {
        val v = LayoutInflater.from(p0?.context)

        val playerItemView = v.inflate(R.layout.cardview_players, p0, false)

          return PlayerViewHolder(playerItemView);


    }


    override fun getItemCount(): Int {

        return playersList.size

    }

    override fun onBindViewHolder(p0: PlayerViewHolder, p1: Int) {


        val dm = DataManager(mainActivity!!)


        val playerToDisplay = playersList.get(p1)

        p0.firstname?.text = playerToDisplay.firstName
        p0.lastname?.text = playerToDisplay.lastName
        p0.position?.text = playerToDisplay.positionPlayed
        p0.injured?.text = playerToDisplay.isInjured.toString()
        p0.age?.text = playerToDisplay.age.toString()

        if (playerToDisplay.isInjured.toString() == "true") {
            p0.itemView.setBackgroundColor(Color.RED)
        }
        if (playerToDisplay.isInjured.toString() == "false") {
            p0.itemView.setBackgroundColor(Color.GREEN)
        }

        //Delete Icon
        val holderDelete = p0 as PlayerViewHolder
        holderDelete.deleteIcon.setTag(p1)
        holderDelete.deleteIcon.setOnClickListener {

            val callingActivity = mainActivity as MainActivity
            callingActivity.openDeleteFragment(
                p0.firstname.text.toString(),
                p0.lastname.text.toString()
            )


        }


        //Edit Icon
        val holderEdit = p0 as PlayerViewHolder
        holderEdit.editIcon.setTag(p1)
        holderEdit.editIcon.setOnClickListener {
            val callingActivity = mainActivity as MainActivity

            callingActivity.openEditFragment(
                p0.firstname.text.toString(),
                p0.lastname.text.toString(),
                p0.position.text.toString(),
                p0.age.text.toString()
            )
        }
    }


}

