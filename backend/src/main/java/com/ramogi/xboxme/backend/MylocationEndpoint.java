package com.ramogi.xboxme.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * WARNING: This generated code is intended as a sample or starting point for using a
 * Google Cloud Endpoints RESTful API with an Objectify entity. It provides no data access
 * restrictions and no data validation.
 * <p/>
 * DO NOT deploy this code unchanged as part of a real application to real users.
 */
@Api(
        name = "mylocationApi",
        version = "v1",
        resource = "mylocation",
        namespace = @ApiNamespace(
                ownerDomain = "backend.xboxme.ramogi.com",
                ownerName = "backend.xboxme.ramogi.com",
                packagePath = ""
        )
)
public class MylocationEndpoint {

    private static final Logger logger = Logger.getLogger(MylocationEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(Mylocation.class);
    }

    /**
     * Returns the {@link Mylocation} with the corresponding ID.
     *
     * @param email the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code Mylocation} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "mylocation/{email}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public Mylocation get(@Named("email") String email) throws NotFoundException {
        logger.info("Getting Mylocation with ID: " + email);
        Mylocation mylocation = ofy().load().type(Mylocation.class).id(email).now();
        if (mylocation == null) {
            throw new NotFoundException("Could not find Mylocation with ID: " + email);
        }
        return mylocation;
    }

    /**
     * Inserts a new {@code Mylocation}.
     */
    @ApiMethod(
            name = "insert",
            path = "mylocation",
            httpMethod = ApiMethod.HttpMethod.POST)
    public Mylocation insert(Mylocation mylocation) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that mylocation.email has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(mylocation).now();
        logger.info("Created Mylocation with ID: " + mylocation.getEmail());

        return ofy().load().entity(mylocation).now();
    }

    /**
     * Updates an existing {@code Mylocation}.
     *
     * @param email      the ID of the entity to be updated
     * @param mylocation the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code email} does not correspond to an existing
     *                           {@code Mylocation}
     */
    @ApiMethod(
            name = "update",
            path = "mylocation/{email}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public Mylocation update(@Named("email") String email, Mylocation mylocation) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(email);
        ofy().save().entity(mylocation).now();
        logger.info("Updated Mylocation: " + mylocation);
        return ofy().load().entity(mylocation).now();
    }

    /**
     * Deletes the specified {@code Mylocation}.
     *
     * @param email the ID of the entity to delete
     * @throws NotFoundException if the {@code email} does not correspond to an existing
     *                           {@code Mylocation}
     */
    @ApiMethod(
            name = "remove",
            path = "mylocation/{email}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("email") String email) throws NotFoundException {
        checkExists(email);
        ofy().delete().type(Mylocation.class).id(email).now();
        logger.info("Deleted Mylocation with ID: " + email);
    }

    /**
     * List all entities.
     *
     * @param cursor used for pagination to determine which page to return
     * @param limit  the maximum number of entries to return
     * @return a response that encapsulates the result list and the next page token/cursor
     */
    @ApiMethod(
            name = "list",
            path = "mylocation",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<Mylocation> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<Mylocation> query = ofy().load().type(Mylocation.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<Mylocation> queryIterator = query.iterator();
        List<Mylocation> mylocationList = new ArrayList<Mylocation>(limit);
        while (queryIterator.hasNext()) {
            mylocationList.add(queryIterator.next());
        }
        return CollectionResponse.<Mylocation>builder().setItems(mylocationList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(String email) throws NotFoundException {
        try {
            ofy().load().type(Mylocation.class).id(email).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find Mylocation with ID: " + email);
        }
    }
}