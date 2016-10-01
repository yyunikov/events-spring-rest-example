package ua.yyunikov;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.yyunikov.domain.event.Event;
import ua.yyunikov.domain.event.EventType;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class EventsApiTest {

    private static final String PATH_PARAM = "path";
    private static final String NAME_PARAM = "name";
    private static final String NAME_PARAM_VALUE = "Yuriy Yunikov";
    private static final String NAME_PARAM_VALUE_UPDATED = "Chuck Norris";
    private static final String PATH_PARAM_VALUE = "http://yunikov.com";
    private static final String EVENTS_PATH = "/events";

    private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void testCrudEvent() throws Exception {
        // HTTP POST
        final Event createdEvent = createEvent();

        // HTTP GET
        mockMvc.perform(MockMvcRequestBuilders
                .get(EVENTS_PATH + "/" + createdEvent.getId())
                .accept(MediaTypes.HAL_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("_links", notNullValue()))
                .andExpect(jsonPath("$.id", is(createdEvent.getId())))
                .andExpect(jsonPath("$.type", is(createdEvent.getType().name())))
                .andExpect(jsonPath("$.ts", is(createdEvent.getTs())))
                .andExpect(jsonPath("$.params." + NAME_PARAM, is(createdEvent.getParams().get(NAME_PARAM))))
                .andExpect(jsonPath("$.params." + PATH_PARAM, is(createdEvent.getParams().get(PATH_PARAM))));

        // HTTP PUT
        createdEvent.getParams().put(NAME_PARAM, NAME_PARAM_VALUE_UPDATED);
        mockMvc.perform(MockMvcRequestBuilders
                .put(EVENTS_PATH + "/" + createdEvent.getId())
                .accept(MediaTypes.HAL_JSON)
                .content(new Gson().toJson(createdEvent))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.params." + NAME_PARAM, is(NAME_PARAM_VALUE_UPDATED)));

        // HTTP PATCH
        createdEvent.getParams().put("type", EventType.SESSION_START);
        mockMvc.perform(MockMvcRequestBuilders
                .patch(EVENTS_PATH + "/" + createdEvent.getId())
                .accept(MediaTypes.HAL_JSON)
                .content(new Gson().toJson(createdEvent.getParams()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type", is(EventType.SESSION_START.name())));

        // HTTP DELETE
        deleteEvent(createdEvent.getId());
    }

    @Test
    public void testPaths() throws Exception {
        testPathOk(EVENTS_PATH, true);
        testPathOk(EVENTS_PATH + "/search", true);
        testPathOk("/profile" + EVENTS_PATH, true);
        testPathOk(EVENTS_PATH + "/abasfegegsg", false);
    }

    @Test
    public void testFindByTs() throws Exception {
        final Event createdEvent = createEvent();
        // one result returned
        mockMvc.perform(MockMvcRequestBuilders
                .get(EVENTS_PATH + "/search/ts?from=" + createdEvent.getTs())
                .accept(MediaTypes.HAL_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("_links", notNullValue()))
                .andExpect(jsonPath("_embedded.events.[0].id", notNullValue()))
                .andExpect(jsonPath("_embedded.events.[0].type", notNullValue()))
                .andExpect(jsonPath("_embedded.events.[0].ts", notNullValue()))
                .andExpect(jsonPath("_embedded.events.[0].params", notNullValue()));
        deleteEvent(createdEvent.getId());
    }

    @Test
    public void testFindByType() throws Exception {
        final Event createdEvent = createEvent();
        // one result returned
        mockMvc.perform(MockMvcRequestBuilders
                .get(EVENTS_PATH + "/search/type?type=" + createdEvent.getType())
                .accept(MediaTypes.HAL_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("_links", notNullValue()))
                .andExpect(jsonPath("_embedded.events.[0].id", notNullValue()))
                .andExpect(jsonPath("_embedded.events.[0].type", notNullValue()))
                .andExpect(jsonPath("_embedded.events.[0].ts", notNullValue()))
                .andExpect(jsonPath("_embedded.events.[0].params", notNullValue()));
        deleteEvent(createdEvent.getId());
    }

    private void testPathOk(final String path, final boolean ok) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(path)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(ok ? status().isOk() : status().isNotFound());
    }

    private Event createEvent() throws Exception {
        final Event event = event();
        final String requestBody = new Gson().toJson(event);

        final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post(EVENTS_PATH)
                .accept(MediaTypes.HAL_JSON)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.type", is(event.getType().name())))
                .andExpect(jsonPath("$.params." + NAME_PARAM, is(event.getParams().get(NAME_PARAM))))
                .andExpect(jsonPath("$.params." + PATH_PARAM, is(event.getParams().get(PATH_PARAM))))
                .andReturn();

        return new Gson().fromJson(mvcResult.getResponse().getContentAsString(), Event.class);
    }

    private void deleteEvent(final String id) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete(EVENTS_PATH + "/" + id)
                .accept(MediaTypes.HAL_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    private Event event() {
        final Map<String, Object> params = new HashMap<>();
        params.put(PATH_PARAM, PATH_PARAM_VALUE);
        params.put(NAME_PARAM, NAME_PARAM_VALUE);
        return new Event(EventType.LINK_CLICKED, params);
    }
}
