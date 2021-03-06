package com.example.android.homepage.ui.profile

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.android.homepage.MainActivity
import com.example.android.homepage.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_profile.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ProfileFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var mAuth: FirebaseAuth

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()

        buttonGoProfile.setOnClickListener {

            view!!.findNavController().navigate(R.id.action_navigation_profile_to_userProfileFragment)
        }

        buttonGoLogout.setOnClickListener{

            //todo logout codes here
            mAuth.signOut()
            var i = Intent(activity, MainActivity::class.java)
            startActivity(i)
            Toast.makeText(activity,"Signed out!", Toast.LENGTH_SHORT).show()
        }

        val user = FirebaseAuth.getInstance().currentUser
        if (user == null) {
            // User is not signed in
            view.findNavController().navigate(R.id.action_navigation_profile_to_loginFragment)
        }
    }

    interface OnFragmentInteractionListener{
        fun onFragmentInteraction(uri: Uri)
    }
}
