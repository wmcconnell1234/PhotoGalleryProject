package com.example.photogalleryproject3;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import FolderUtil.FolderUtil;
import Utils2.Utils2;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)       //ADDED FOR ROBOLECTRIC http://robolectric.org/getting-started/
@Config(sdk = 27)                           //added https://stackoverflow.com/questions/56821193/does-robolectric-require-java-9
public class FolderTest
{
    @Test
    public void CanViewFolder()
    {
        //Given there is a picture called "Caption 1" in Folder 1, and user wants to view Folder 1...
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
        int currentElement = 0;
        Utils2 U = new Utils2();
        //Initialize folder list
        List folderList = new ArrayList();
        List tempList = new ArrayList();
        tempList.add("Folder 1");
        tempList.add(filenameListM.get(currentElement));
        folderList.add(tempList);
        //Initialize intent
        Intent i = new Intent();
        i.putExtra("FOLDER", "Folder 1");

        //...When I call ViewFolder()...
        FolderUtil F = new FolderUtil();
        List result = new ArrayList();
        result = F.ViewFolder(i, filenameListF, captionListF, dateListF,
                filenameListM, captionListM, dateListM, U, folderList);
        //Unpack result
        filenameListF = (List) result.get(0);
        captionListF = (List) result.get(1);
        dateListF = (List) result.get(2);

        //...am I viewing the contents of folder 1?
        assertEquals("Caption 1", captionListF.get(0));

    }//end CanViewFolder
}//end FolderTest
