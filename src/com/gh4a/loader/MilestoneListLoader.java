package com.gh4a.loader;

import java.io.IOException;
import java.util.HashMap;

import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.MilestoneService;

import android.content.Context;

import com.gh4a.Constants.LoaderResult;
import com.gh4a.Gh4Application;

public class MilestoneListLoader extends BaseLoader {

    private String mRepoOwner;
    private String mRepoName;
    private String mState;
    
    public MilestoneListLoader(Context context, String repoOwner, String repoName, String state) {
        super(context);
        mRepoOwner = repoOwner;
        mRepoName = repoName;
        mState = state;
    }
    
    @Override
    public void doLoadInBackground(HashMap<Integer, Object> result) throws IOException {
        Gh4Application app = (Gh4Application) getContext().getApplicationContext();
        GitHubClient client = new GitHubClient();
        client.setOAuth2Token(app.getAuthToken());
        MilestoneService milestoneService = new MilestoneService(client);
        result.put(LoaderResult.DATA, milestoneService.getMilestones(mRepoOwner, mRepoName, mState));
    }
}
