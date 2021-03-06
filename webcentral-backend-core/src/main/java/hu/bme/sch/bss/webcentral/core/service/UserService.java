package hu.bme.sch.bss.webcentral.core.service;

import hu.bme.sch.bss.webcentral.core.dao.UserDao;
import hu.bme.sch.bss.webcentral.core.domain.UserRequest;
import hu.bme.sch.bss.webcentral.core.model.Position;
import hu.bme.sch.bss.webcentral.core.model.Status;
import hu.bme.sch.bss.webcentral.core.model.User;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public final class UserService {

    private static final String USER_CREATE_STARTED = "User creation started. {}";
    private static final String USER_CREATE_SUCCEED = "User creation succeed. {}";
    private static final String USER_SEARCH_STARTED = "User search started. {}";
    private static final String USER_SEARCH_SUCCEED = "User search succeed. {}";
    private static final String USER_NOT_FOUND = "User not found with id {}";
    private static final String USERS_ALL_SEARCH_STARTED = "Search for all users started";
    private static final String USERS_ALL_SEARCH_SUCCEED = "Search for all users succeed";
    private static final String USERS_ARCHIVED_SEARCH_STARTED = "Search for all archived user started";
    private static final String USERS_ARCHIVED_SEARCH_SUCCEED = "Search for all archived user succeed";
    private static final String USER_UPDATE_STARTED = "User update started. {}";
    private static final String USER_UPDATE_SUCCEED = "User update succeed. {}";
    private static final String USER_ARCHIVE_STARTED = "User archive started. {}";
    private static final String USER_ARCHIVE_SUCCEED = "User archive succeed. {}";
    private static final String USER_RESTORE_STARTED = "User restore started. {}";
    private static final String USER_RESTORE_SUCCEED = "User restore succeed. {}";
    private static final String USER_DELETE_STARTED = "User delete started. {}";
    private static final String USER_DELETE_SUCCEED = "User delete succeed. {}";
    private static final String USER_STATUS_UPDATE_STARTED = "User status update started. {}\n" + "New Status: {}";
    private static final String USER_STATUS_UPDATE_SUCCEED = "User status update started. {}\n" + "New Status: {}";
    private static final String USER_POSITION_UPDATE_STARTED = "User status update started. {}\n" + "New Status: {}";
    private static final String USER_POSITION_UPDATE_SUCCEED = "User status update started. {}\n" + "New Status: {}";

    private final UserDao userDao;
    private final Logger logger;

    UserService(final UserDao userDao, final Logger logger) {
        this.userDao = userDao;
        this.logger = logger;
    }

    public User create(final UserRequest request, final Status status, final Position position) {
        logger.info(USER_CREATE_STARTED, request);
        final User user = User.builder()
            .withDescription(request.getDescription())
            .withEmail(request.getEmail())
            .withFamilyName(request.getFamilyName())
            .withGivenName(request.getGivenName())
            .withImageUri(request.getImageUri())
            .withNickname(request.getNickname())
            .withStatus(status)
            .withPosition(position)
            .build();
        final User userResult = userDao.save(user);
        logger.info(USER_CREATE_SUCCEED, user);
        return userResult;
    }

    public User update(final UserRequest request, final User user) {
        logger.info(USER_UPDATE_STARTED, user);
        user.setNickname(request.getNickname());
        user.setGivenName(request.getGivenName());
        user.setFamilyName(request.getFamilyName());
        user.setEmail(request.getEmail());
        user.setDescription(request.getDescription());
        user.setImageUri(request.getImageUri());
        final User result = userDao.save(user);
        logger.info(USER_UPDATE_SUCCEED, user);
        return result;
    }

    public User archive(final User user) {
        logger.info(USER_ARCHIVE_STARTED, user);
        user.setArchived(true);
        final User result = userDao.save(user);
        logger.info(USER_ARCHIVE_SUCCEED, user);
        return result;
    }

    public User restore(final User user) {
        logger.info(USER_RESTORE_STARTED, user);
        user.setArchived(false);
        final User result = userDao.save(user);
        logger.info(USER_RESTORE_SUCCEED, user);
        return result;
    }

    public void delete(final User user) {
        logger.info(USER_DELETE_STARTED, user);
        userDao.delete(user);
        logger.info(USER_DELETE_SUCCEED, user);
    }

    public User findById(final Long id) {
        logger.info(USER_SEARCH_STARTED, id);
        return userDao.findById(id).map(user -> {
            logger.info(USER_SEARCH_SUCCEED, id);
            return user;
        }).orElseThrow(() -> {
            logger.warn(USER_NOT_FOUND, id);
            return new NoSuchElementException("User Not Found.");
        });
    }

    public List<User> findAll() {
        logger.info(USERS_ALL_SEARCH_STARTED);
        final List<User> userList = userDao.findAll();
        logger.info(USERS_ALL_SEARCH_SUCCEED);
        return userList;
    }

    public List<User> findArchived() {
        logger.info(USERS_ARCHIVED_SEARCH_STARTED);
        final List<User> archivedUserList = userDao.findAllArchived();
        logger.info(USERS_ARCHIVED_SEARCH_SUCCEED);
        return archivedUserList;
    }

    public User updateUserStatus(final User user, final Status status) {
        logger.info(USER_STATUS_UPDATE_STARTED, user, status);
        user.setStatus(status);
        final User result = userDao.save(user);
        logger.info(USER_STATUS_UPDATE_SUCCEED, user, status);
        return result;
    }

    public User updateUserPosition(final User user, final Position position) {
        logger.info(USER_POSITION_UPDATE_STARTED, user, position);
        user.setPosition(position);
        final User result = userDao.save(user);
        logger.info(USER_POSITION_UPDATE_SUCCEED, user, position);
        return result;
    }
}
