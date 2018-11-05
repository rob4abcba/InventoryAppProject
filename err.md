
Erick Ruelas [ABND] [Oct 30th at 9:37 PM]
https://github.com/Codeerick/InventoryAppProject.git
GitHub
Codeerick/InventoryAppProject
Contribute to Codeerick/InventoryAppProject development by creating an account on GitHub.
 
(edited)


3 replies
Erick Ruelas [ABND] [5 days ago]
having issues in the catalog activity been having errors in that area to populate the list activity wondering if i formatted
wrong ?


Jose ABND [5 days ago]
move line 29 and 30:
private static final int  EXISTING_INVENTORY_LOADER = 0;
       InventoryCursorAdapter mCursorAdapter;

outside of the Oncreate


Jose ABND [5 days ago]
fab is missing an id in the catalog activity.xml... android:id="@+id/fab"
