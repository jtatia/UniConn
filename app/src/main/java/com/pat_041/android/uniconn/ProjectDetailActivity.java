package com.pat_041.android.uniconn;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pat_041.android.uniconn.definitions.Project;
import com.pat_041.android.uniconn.definitions.User;

public class ProjectDetailActivity extends AppCompatActivity {
    private Button mContributeButton;
    private TextView mProjectNameTextView;
    private TextView mUserNameTextView;
    private TextView mUserInfoTextView;
    private TextView mProjectDetailTextView;
    private Project mProject;
    private ImageView imageView;
    private User mLoggedInUser;
    private User mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);
        mContributeButton=(Button)findViewById(R.id.contribute_button);
        mProjectDetailTextView=(TextView)findViewById(R.id.tv_project_detail);
        mProjectNameTextView=(TextView)findViewById(R.id.tv_project_name);
        mUserInfoTextView=(TextView)findViewById(R.id.tv_user_info);
        mUserNameTextView=(TextView)findViewById(R.id.tv_user_name);
        imageView=(ImageView)findViewById(R.id.profile_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProjectDetailActivity.this,UserDetailActivity.class);
                i.putExtra("UserObj",mUser);
                startActivity(i);
            }
        });
        mProject = (Project)getIntent().getSerializableExtra("ProjectObj");
        mUser = (User)getIntent().getSerializableExtra("UserObj");
        mLoggedInUser=(User)getIntent().getSerializableExtra("User");
        populateData();
        mContributeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setType("text/plain");
                //sendIntent.setData(Uri.parse(mUser.getEmail()));
                sendIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { mLoggedInUser.getEmail() });
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Contribution Request for "+mProject.getName());
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Dear "+mUser.getName()+",\n I am very much interested in your project idea and would like to contribute.\n Thank you. \n ");
                startActivity(sendIntent);*/
                Intent mailClient = new Intent(Intent.ACTION_VIEW);
                mailClient.setClassName("com.google.android.gm", "com.google.android.gm.ConversationListActivity");
                startActivity(mailClient);
            }
        });
    }
    private void populateData() {
        mProjectNameTextView.setText(mProject.getName());
        mUserNameTextView.setText(mUser.getName());
        mUserInfoTextView.setText(mUser.getType()+"\n"+mUser.getInstitute());
        mProjectDetailTextView.setText("PROJECT TAG : "+mProject.getTag()+"\n\n");
        mProjectDetailTextView.append("Start Date : "+mProject.getSdate()+"\n\n");
        mProjectDetailTextView.append("Project Duration : "+mProject.getDuration()+"\n\n");
        mProjectDetailTextView.append("Location : "+mProject.getLocation()+"\n\n");
        mProjectDetailTextView.append("Project Overview : "+mProject.getInfo()+"\n\n");
    }
}
