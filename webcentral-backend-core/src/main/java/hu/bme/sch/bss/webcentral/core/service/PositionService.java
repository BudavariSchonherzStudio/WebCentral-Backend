package hu.bme.sch.bss.webcentral.core.service;

import hu.bme.sch.bss.webcentral.core.dao.PositionDao;
import hu.bme.sch.bss.webcentral.core.domain.PositionRequest;
import hu.bme.sch.bss.webcentral.core.model.Position;
import hu.bme.sch.bss.webcentral.core.model.User;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;


@Component
@SuppressWarnings("designforextension")
public class PositionService {

    private static final String POSITION_CREATE_STARTED = "Position creation started. {}";
    private static final String POSITION_CREATE_SUCCEED = "Position creation succeed. {}";
    private static final String POSITION_SEARCH_STARTED = "Position search started {}";
    private static final String POSITION_SEARCH_SUCCEED = "Position search succeed {}";
    private static final String POSITION_NOT_FOUND = "Position not found with id: {}";
    private static final String POSITION_DELETE_STARTED = "Position delete started. {}";
    private static final String POSITION_DELETE_SUCCEED = "Position delete succeed. {}";
    private static final String POSITION_EDIT_STARTED = "Position edit started. {}";
    private static final String POSITION_EDIT_SUCCEED = "Position edit succeed. {}";
    private static final String POSITION_ALL_SEARCH_STARTED = "Search for all position started";
    private static final String POSITION_ALL_SEARCH_SUCCEED = "Search for all position succeed";
    private static final String POSITION_FIND_ALL_USERS_WITH_POSITION_STARTED = "Search for all user started with position of {}";
    private static final String POSITION_FIND_ALL_USERS_WITH_POSITION_SUCCEED = "Search for all user succeed with position of {}";

    private final PositionDao positionDao;
    private final Logger logger;

    public PositionService(final PositionDao positionDao, final Logger logger) {
        this.positionDao = positionDao;
        this.logger = logger;
    }

    public Position create(final PositionRequest request) {
        logger.info(POSITION_CREATE_STARTED, request);
        final Position position = createPositionWithRequestData(request);
        positionDao.save(position);
        logger.info(POSITION_CREATE_SUCCEED, request);
        return position;
    }

    public Position findById(final Long id) {
        logger.info(POSITION_SEARCH_STARTED, id);
        final Optional<Position> result = positionDao.findById(id);
        if (result.isEmpty()) {
            logger.warn(POSITION_NOT_FOUND, id);
            throw new NoSuchElementException("Position not found");
        }
        logger.info(POSITION_SEARCH_SUCCEED, id);
        return result.get();
    }

    public void delete(final Position position) {
        logger.info(POSITION_DELETE_STARTED, position);
        positionDao.delete(position);
        logger.info(POSITION_DELETE_SUCCEED, position);
    }

    Position createPositionWithRequestData(final PositionRequest request) {
        return Position.builder()
            .withName(request.getName())
            .build();
    }

    public void update(final PositionRequest request, final Position position) {
        logger.info(POSITION_EDIT_STARTED, request);
        position.setName(request.getName());
        logger.info(POSITION_EDIT_SUCCEED, request);
    }

    public List<Position> findAll() {
        logger.info(POSITION_ALL_SEARCH_STARTED);
        final List<Position> positionList = positionDao.findAll();
        logger.info(POSITION_ALL_SEARCH_SUCCEED);
        return positionList;
    }

    public Set<User> findAllUserWithPositionOf(final Position position) {
        logger.info(POSITION_FIND_ALL_USERS_WITH_POSITION_STARTED, position);
        final Set<User> users = position.getUsers();
        logger.info(POSITION_FIND_ALL_USERS_WITH_POSITION_SUCCEED, position);
        return users;
    }
}
