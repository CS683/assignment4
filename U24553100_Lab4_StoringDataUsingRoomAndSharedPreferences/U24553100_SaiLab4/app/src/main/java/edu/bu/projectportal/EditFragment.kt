package edu.bu.projectportal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import edu.bu.projectportal.databinding.FragmentEditBinding
import edu.bu.projectportal.datalayer.Project

class EditFragment : Fragment() {
    // use ViewBinding
    private var _binding: FragmentEditBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)

        val position:Int = arguments?.getInt("projId")?:0
        Log.d("TAG","position:"+position)
        val projectPortalDatabaseInst = (requireContext().applicationContext as ProjectPortalApplication).projectportalDatabase
        val projectDao = projectPortalDatabaseInst.projectDao()
        val project = projectDao.searchProjectById(position.toLong())
        binding.editProjTitleId.setText(project.title)
        binding.authorsId.setText( project.authors)
        binding.projLinkEdit.setText(project.links)
        binding.editFavId.setChecked(project.favorite)

        binding.submit.setOnClickListener {
            project.title = binding.editProjTitleId.text.toString()
            project.authors = binding.authorsId.text.toString()
            project.links = binding.projLinkEdit.text.toString()
            project.favorite = binding.editFavId.isChecked
            projectDao.updateProject(project)

            view.findNavController().
            navigate(R.id.action_editFragment_pop)
        }

        binding.cancelId.setOnClickListener {
            view.findNavController().
            navigate(R.id.action_editFragment_pop)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


