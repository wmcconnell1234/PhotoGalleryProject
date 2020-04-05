package FolderUtil;
import android.content.Intent;
import java.util.ArrayList;
import java.util.List;
import Utils2.Utils2;

public class FolderUtil
{
    public List ViewFolder(Intent data, List filenameListF, List captionListF, List dateListF,
                           List filenameListM, List captionListM, List dateListM, Utils2 U, List folderList)
    {
        //If the folder that was chosen is "All" then just clear the filters
        String folderName = data.getStringExtra("FOLDER");
        if(folderName.equals("All"))
        {
            filenameListF = U.copy(filenameListM);
            captionListF = U.copy(captionListM);
            dateListF = U.copy(dateListM);
        }
        else
        {
            //Navigate to the folder that was chosen
            String folderNameRead = "null";
            int index;
            for (index = 0; index < folderList.size() && !folderNameRead.equals(folderName); index++)
                folderNameRead = ((ArrayList) folderList.get(index)).get(0).toString();
            index--; //I assume there is at least one folder.
            List currentFolder = new ArrayList();
            currentFolder = (ArrayList) folderList.get(index);

            //Set filtered lists to the files in that folder
            filenameListF.clear();
            captionListF.clear();
            dateListF.clear();
            for (int i = 1; i < currentFolder.size(); i++) {
                //Filename
                String filename = currentFolder.get(i).toString();
                filenameListF.add(filename);

                //Preparatory work for date and caption (determine current file index in master lists)
                String fileNameRead = "null";
                int fileIndex;
                for (fileIndex = 0; fileIndex < filenameListM.size() && !fileNameRead.equals(filename); fileIndex++)
                    fileNameRead = filenameListM.get(fileIndex).toString();
                fileIndex--;

                //Caption
                String caption = captionListM.get(fileIndex).toString();
                captionListF.add(caption);

                //Date
                String date = dateListM.get(fileIndex).toString();
                dateListF.add(date);
            }
        }//end else
        //Pack the filtered lists into a list of lists and return it
        List result = new ArrayList();
        result.add(filenameListF);
        result.add(captionListF);
        result.add(dateListF);
        return result;
    }//end ViewFolder
}
