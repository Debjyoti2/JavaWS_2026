package thread.large_data_processing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import static com.pwb.synchronized_keyword.ThreadRunner.i;

public class DataProcessing {

    static void main() {

        Long deptId= 1L;
        processUsers(deptId);

    }


   //@Transactional
   public static void processUsers(Long deptId){

        int start =0;
        int pageSize = 500;
        Page<User> userPage;

        do{
            orderPage = orderRepository.findAll(PageRequest.of(start,pageSize));
            orderPage.getContent().forEach(users->{
                processBatch(users);
            });
            entityManager.clear();//unload 500 data from JVM memory.
            page++;
        }while(orderPage.hasNext());

   }

    public static List<EnrichedUser> processBatch(List<User> users) {
        try(var executor = Executors.newVirtualThreadPerTaskExecutor()){
           List<Future<EnrichedUser>> enrichUsers =users.stream().map(user->
                executor.submit(()-> enrichUser(user))).toList();
       // 500 users are coming here, each user is processed in a separate  virtual thread

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
