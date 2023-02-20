package dev.thesmartone.servermanager.service.implementation;

import dev.thesmartone.servermanager.enumeration.Status;
import dev.thesmartone.servermanager.model.Server;
import dev.thesmartone.servermanager.repository.ServerRepo;
import dev.thesmartone.servermanager.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Random;

import static dev.thesmartone.servermanager.enumeration.Status.SERVER_DOWN;
import static dev.thesmartone.servermanager.enumeration.Status.SERVER_UP;
import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ServerServiceImpl implements ServerService {

    private final ServerRepo serverRepo;
    @Override
    public Server create(Server server) {
        log.info("Saving new server: {}", server.getName());
        server.setImgUrl(setServerUrl());
        return serverRepo.save(server);
    }

    private String setServerUrl() {
        String[] imageNames = {"server1.png", "server2.png", "server3.png", "server4.png"};
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/server/image/" + imageNames[new Random().nextInt(4)]).toUriString();
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching all servers");
        return serverRepo.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching server by id: {}", id);
        return serverRepo.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Updating server: {}", server.getName());
        return serverRepo.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server by id: {}", id);
        serverRepo.deleteById(id);
        return TRUE;
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server IP: {}", ipAddress);
        Server server = serverRepo.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? SERVER_UP : SERVER_DOWN);
        serverRepo.save(server);
        return server;
    }
}
