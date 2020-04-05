package DeleteUtil;
import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import Utils2.Utils2;

public class DeleteUtil
{
    public void Delete(Context c, List filenameListF, List folderList, int currentElement, List filenameListM,
                       List captionListF, List captionListM, List dateListF, List dateListM, Utils2 U)
    {
        //Only delete if there's at least one picture
        if (filenameListF.size() > 0) {
            //0. Delete current picture from all folders that it's in
            for (int i = 0; i < folderList.size(); i++) {
                int size = ((ArrayList) folderList.get(i)).size();
                for (int j = 1; j < size; j++) {
                    if (((ArrayList) folderList.get(i)).get(j).toString().equals(filenameListF.get(currentElement).toString())) {
                        ((ArrayList) folderList.get(i)).remove(j);
                        size--;
                        j--;
                    }
                }
            }
            //1. Delete current picture from the file system
            File f = new File(
                    c.getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/" + filenameListF.get(currentElement).toString());
            f.delete();
            //2. Delete current picture from master filename list
            String s = filenameListF.get(currentElement).toString();
            int i = filenameListM.indexOf(s);
            filenameListM.remove(i);
            //3. Delete current picture from master caption list
            s = captionListF.get(currentElement).toString();
            i = captionListM.indexOf(s);
            captionListM.remove(i);
            //4. Delete current picture from master date list
            s = dateListF.get(currentElement).toString();
            i = dateListM.indexOf(s);
            dateListM.remove(i);
            //5. Delete current picture from filtered filename list
            filenameListF.remove(currentElement);
            //6. Delete current picture from filtered caption list
            captionListF.remove(currentElement);
            //7. Delete current picture from filtered date list
            dateListF.remove(currentElement);
            //8. Delete current picture from the caption file
            U.SaveToFile(c, captionListM, "captions");
            //9. Delete current picture from the date file
            U.SaveToFile(c, dateListM, "dates");
        }//end if
    }//end Delete
}
