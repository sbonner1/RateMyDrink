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
import Model.User;

import cs.ycp.edu.cs481.ratemydrink.R;
import cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers.RegisterUserAsync;


/**
 * Fragement to register a new user with the backend.
 */
public class RegisterFragment extends Fragment {

    private EditText username, password, confirmPassword, email;

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

        Button Rsubmit = (Button) rootView.findViewById(R.id.registerSubmit);
        Rsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Check for validity
                if(!password.getText().toString().equals(confirmPassword.getText().toString())){
                    Toast.makeText(getActivity(), "Passwords don't match!", Toast.LENGTH_SHORT).show();
                }
                if(username.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(), "Username not entered!", Toast.LENGTH_SHORT).show();
                }
                if(password.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(), "Password not entered!", Toast.LENGTH_SHORT).show();
                }
                if(confirmPassword.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(), "Retype Password!", Toast.LENGTH_SHORT).show();
                }
                if(email.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(), "Email not entered!", Toast.LENGTH_SHORT).show();
                }
                else {
                    RegisterUserAsync registerUser = new RegisterUserAsync();
                    registerUser.execute(createUser());
                    Toast.makeText(getActivity(), "User registered.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
