package com.example.photogalleryproject3;
import android.content.Context;
import android.os.Environment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import DeleteUtil.DeleteUtil;
import Utils2.Utils2;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)       //ADDED FOR ROBOLECTRIC http://robolectric.org/getting-started/
@Config(sdk = 27)                           //added https://stackoverflow.com/questions/56821193/does-robolectric-require-java-9
public class DeleteTest
{
    @Test
    public void CanDeletePicture()
    {
        //Given there is a picture in the photo gallery, and it is being displayed...
        Context c = getApplicationContext();
        File f = null;
        f = c.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        try{
            File.createTempFile("img1", ".JPEG", f);
        } catch (IOException e) {}
        List filenameListF = new ArrayList();
        List filenameListM = new ArrayList();
        File[] storageDirFlist;
        storageDirFlist = f.listFiles();
        String fileName = storageDirFlist[0].getAbsolutePath();
        filenameListM.add(fileName);
        filenameListF.add(fileName);
        List captionListM = new ArrayList();
        List captionListF = new ArrayList();
        captionListM.add("Caption 1");
        captionListF.add("Caption 1");
        List dateListM = new ArrayList();
        List dateListF = new ArrayList();
        dateListM.add("Mon Jan 20 08:00:00 PST 2020");
        dateListF.add("Mon Jan 20 08:00:00 PST 2020");
        List folderList = new ArrayList(); //just leave it blank, to say that the picture isn't in any folders.
        int currentElement = 0;
        Utils2 U = new Utils2();
        File f2 = null;
        f2 = c.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File captionFile = null;
        try{
            captionFile = File.createTempFile("captions", ".txt", f2);
        } catch (IOException e) {}
        File f3 = null;
        f3 = c.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File dateFile = null;
        try{
            dateFile = File.createTempFile("dates", ".txt", f3);
        } catch (IOException e) {}

        //...When I call Delete()...
        DeleteUtil D = new DeleteUtil();
        D.Delete(c, filenameListF, folderList, currentElement, filenameListM,
                captionListF, captionListM, dateListF, dateListM, U);

        //...Did the file get deleted?
        assertEquals(0, filenameListM.size());
        assertEquals(0, filenameListF.size());
        assertEquals(0, captionListM.size());
        assertEquals(0, captionListF.size());
        assertEquals(0, dateListM.size());
        assertEquals(0, dateListF.size());
    }//end CanDeletePicture
}//end DeleteTest
