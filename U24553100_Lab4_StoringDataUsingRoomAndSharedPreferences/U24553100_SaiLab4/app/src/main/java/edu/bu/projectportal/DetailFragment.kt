package edu.bu.projectportal

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageButton
import android.widget.Switch
import android.widget.TextView
import androidx.navigation.findNavController
import edu.bu.projectportal.datalayer.Project

class DetailFragment : Fragment(R.layout.fragment_detail) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)

        val editProj = view.findViewById<ImageButton>(R.id.editProj)

        val favButton = view.findViewById<Switch>(R.id.favid)
        val position:Int = arguments?.getInt("projId")?:0
        Log.d("TAG","position:"+position)
        val projectPortalDatabaseInst = (requireContext().applicationContext as ProjectPortalApplication).projectportalDatabase
        val projectDao = projectPortalDatabaseInst.projectDao()
        val project = projectDao.searchProjectById(position.toLong())
        view.findViewById<TextView>(R.id.projTitle2).setText(project.title)
        view.findViewById<TextView>(R.id.projAuthors).setText( project.authors)
        view.findViewById<TextView>(R.id.projLinks).setText(project.links)
        view.findViewById<Switch>(R.id.favid).isChecked = project.favorite


        editProj.setOnClickListener{
            val action = DetailFragmentDirections
                .actionDetailFragmentToEditFragment(position)
            view.findNavController().navigate(action)
//            view.findNavController().
//            navigate(R.id.action_detailFragment_to_editFragment)
        }

        favButton.setOnClickListener {

        }
    }
}