package cs.ycp.edu.cs481.ratemydrink.fragements;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.rateMyDrink.modelClasses.User;

import cs.ycp.edu.cs481.ratemydrink.R;
import cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers.RegisterUserAsync;


/**
 * Created by Aaron on 3/19/2015.
 */
public class RegisterFragment extends Fragment {

    private EditText username, password, confirmPassword, email;
    private Button Rsubmit;

    /**
     * The serialization (saved instance state) Bundle key representing the
     * activated item position. Only used on tablets.
     */
    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);

        username = (EditText) rootView.findViewById(R.id.desiredUsername_field);
        password = (EditText) rootView.findViewById(R.id.desiredpassword_field);
        confirmPassword = (EditText) rootView.findViewById(R.id.confirmPassword_field);
        email = (EditText) rootView.findViewById(R.id.email_field);


        Rsubmit = (Button) rootView.findViewById(R.id.registerSubmit);
        Rsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Check for validity
                if(password.toString() != confirmPassword.toString()){
                    Toast.makeText(getActivity(), "Passwords don't match!", Toast.LENGTH_SHORT).show();
                }

                if(username.toString() == "")
                {
                    Toast.makeText(getActivity(), "Username not entered!", Toast.LENGTH_SHORT).show();
                }
                if(password.toString() == "")
                {
                    Toast.makeText(getActivity(), "Password not entered!", Toast.LENGTH_SHORT).show();
                }
                if(confirmPassword.toString() == "")
                {
                    Toast.makeText(getActivity(), "Retype Password!", Toast.LENGTH_SHORT).show();
                }
                if(email.toString() == "")
                {
                    Toast.makeText(getActivity(), "Email not entered!", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Post Registration here
                    Toast.makeText(getActivity(), "Success, do post here!", Toast.LENGTH_SHORT).show();
                    RegisterUserAsync registerUser = new RegisterUserAsync();
                    registerUser.execute(createUser());
                    Toast.makeText(getActivity(), "user registered.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            //setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * creates a new User object from the information entered in the RegistrationFragment
     *
     * @return a new User object containing then entered information
     */
    private User createUser(){
        String Username = username.getText().toString();
        String Password = password.getText().toString();
        return new User(Username, Password);
    }
}
