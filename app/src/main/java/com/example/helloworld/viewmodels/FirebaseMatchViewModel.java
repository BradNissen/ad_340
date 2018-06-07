package com.example.helloworld.viewmodels;


        import com.example.helloworld.MatchesItemFragment;
        import com.example.helloworld.datamodels.FirebaseMatchModel;
        import com.example.helloworld.models.MatchItem;

//import com.example.mcnutt.inclassdemo.datamodels.FirebaseTodoModel;
//import com.example.mcnutt.inclassdemo.models.TodoItem;
        import com.google.firebase.database.DataSnapshot;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.function.Consumer;

public class   FirebaseMatchViewModel {

    private FirebaseMatchModel matchModel;

    public FirebaseMatchViewModel() {
        matchModel = new FirebaseMatchModel();
    }

    public void addMatchItem(MatchItem item) {
        matchModel.addMatchItem(item);
    }

    public void getMatchItems(Consumer<ArrayList<MatchItem>> responseCallback) {
        matchModel.getMatchItems(
                (DataSnapshot dataSnapshot) -> {
                    ArrayList<MatchItem> matchItems = new ArrayList<>();
                    for (DataSnapshot matchSnapshot : dataSnapshot.getChildren()) {
                        MatchItem item = matchSnapshot.getValue(MatchItem.class);
                        assert item != null;
                        item.uid = matchSnapshot.getKey();
                        matchItems.add(item);
                    }
                    responseCallback.accept(matchItems);
                },
                (databaseError -> System.out.println("Error reading Match Items: " + databaseError))
        );
    }

    public void updateMatchItem(MatchItem item) {
        matchModel.updateTodoItemById(item);
    }

    public void clear() {
        matchModel.clear();
    }
}