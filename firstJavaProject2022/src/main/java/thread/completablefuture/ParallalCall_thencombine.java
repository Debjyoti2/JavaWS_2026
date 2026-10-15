package thread.completablefuture;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ParallalCall_thencombine {

	public static void main(String[] args) {

		ParallalCall_thencombine obj = new ParallalCall_thencombine();

		System.out.println("Start :: " + LocalDateTime.now());

		CompletableFuture<String> helloc = CompletableFuture.supplyAsync(() -> obj.hello())
				.thenApply(e -> e.toUpperCase());

		CompletableFuture<String> worldc = CompletableFuture.supplyAsync(() -> obj.world())
				.thenApply(e -> e.toUpperCase());

		String result = helloc.thenCombine(worldc, (e1, e2) -> e1 + e2).join();

		System.out.println("Result :: " + result);

		System.out.println("Start :: " + LocalDateTime.now());


		//CompletableFuture

		CompletableFuture.supplyAsync(()->getUserData())
				.thenApply(users-> enrichUser(users))
				.thenAccept(users->savetoDb(users));

	}

	public static List<String> getUserData() {

		List<String> users = new ArrayList<>();
		users.add("DEBJ");
		users.add("ROHIT");
		return users;

	}

	public static List<String> enrichUser(List<String> users) {
		List<String> usersModified = new ArrayList<>();
		usersModified = users.stream().map(user->user.toLowerCase().concat(" :: Title")).collect(Collectors.toList());
		return usersModified;
	}

	public static void savetoDb(List<String> users){
		users.stream().forEach(e->System.out.println("Saved to DB :: " + e));
	}

	public String hello() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "Hello";

	}

	public String world() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "World";

	}





}
