package org.jenkinsci.plugins.cloverphp.results;

import hudson.model.AbstractBuild;
import org.jenkinsci.plugins.cloverphp.CloverBuildAction;

import java.io.IOException;

import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

/**
 * Clover Coverage results for the entire project.
 * @author Stephen Connolly
 * @author Seiji Sogabe
 */
public class ProjectCoverage extends BaseCoverage {

    private int files;

    private int classes;

    private int loc;

    private int ncloc;

    public int getClasses() {
        return classes;
    }

    public void setClasses(int classes) {
        this.classes = classes;
    }

    public int getLoc() {
        return loc;
    }

    public void setLoc(int loc) {
        this.loc = loc;
    }

    public int getNcloc() {
        return ncloc;
    }

    public void setNcloc(int ncloc) {
        this.ncloc = ncloc;
    }

    public int getFiles() {
        return files;
    }

    public void setFiles(int files) {
        this.files = files;
    }

    public BaseCoverage getDynamic(String token, StaplerRequest req, StaplerResponse rsp) throws IOException {
        return findChild(token);
    }

    @Override
    public BaseCoverage getPreviousResult() {
        CloverBuildAction action = getPreviousCloverBuildAction();
        if (action == null) {
            return null;
        }
        return action.getResult();
    }

    @Override
    public void setOwner(AbstractBuild owner) {
        super.setOwner(owner);
        for (BaseCoverage p : getChildren()) {
            p.setOwner(owner);
        }
    }
}
