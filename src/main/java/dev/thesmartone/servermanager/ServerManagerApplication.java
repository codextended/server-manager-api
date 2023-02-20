package dev.thesmartone.servermanager;

import dev.thesmartone.servermanager.enumeration.Status;
import dev.thesmartone.servermanager.model.Server;
import dev.thesmartone.servermanager.repository.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static dev.thesmartone.servermanager.enumeration.Status.SERVER_DOWN;
import static dev.thesmartone.servermanager.enumeration.Status.SERVER_UP;

@SpringBootApplication
public class ServerManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerManagerApplication.class, args);
	}

//	@Bean
//	CommandLineRunner run(ServerRepo serverRepo) {
//		return args -> {
//			serverRepo.save(
//					new Server(
//							null,
//							"192.168.1.160",
//							"Ubuntu Linux",
//							"16 GB",
//							"Personal PC",
//							"http://localhost:8088/server/image/server1.png",
//							SERVER_UP
//					)
//			);
//			serverRepo.save(
//					new Server(
//							null,
//							"74.208.80.227",
//							"Ionos Server",
//							"64 GB",
//							"Cloud Server",
//							"http://localhost:8088/server/image/server2.png",
//							SERVER_DOWN
//					)
//			);
//		};
//	}

}
