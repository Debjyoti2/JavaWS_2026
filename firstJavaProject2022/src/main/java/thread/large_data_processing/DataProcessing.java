package thread.large_data_processing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class DataProcessing {

    static void main() {


        List<User> users = new ArrayList<>();
        //Let say there are 1 Lakh users inside this arraylist.
        //We need to enrich each user with credit score.

        //add 1000000  users to the list for demo
        for (int i = 0; i < 1000000; i++) {
            users.add(new User(i, "User" + i, "user" + i + "@gmail.com"));
        }


        List<List<User>> batches = new ArrayList<>();
        int batchsize=10000;
        for(int i=0;i<batchsize;i+=batchsize){
            var batch = users.subList(i, i+batchsize);
            batches.add(batch);
        }


        for(int i=0;i<batches.size();i++){
            List<EnrichedUser> enrichedUsers = processBatch(batches.get(i));
            System.out.println("enrichedUsers :: " + enrichedUsers);
        }

    }


    public static List<EnrichedUser> processBatch(List<User> users) {
        try(var executor = Executors.newVirtualThreadPerTaskExecutor()){
           List<Future<EnrichedUser>> enrichUsers =users.stream().map(user->
                executor.submit(()-> enrichUser(user))).toList();

           return enrichUsers.stream().map(e-> {
               try {
                   return e.get();
               } catch (InterruptedException ex) {
                   throw new RuntimeException(ex);
               } catch (ExecutionException ex) {
                   throw new RuntimeException(ex);
               }
           }).toList();
        }
    }

    public static EnrichedUser enrichUser(User user) {
        EnrichedUser enrichedUser = EnrichedUser.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .creditScore(100)
                .build();
        return enrichedUser;
    }
}
