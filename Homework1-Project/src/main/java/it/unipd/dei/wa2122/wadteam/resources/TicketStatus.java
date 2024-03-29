package it.unipd.dei.wa2122.wadteam.resources;

import org.json.JSONObject;

public class TicketStatus implements Resource{

    private final Integer id;
    private final TicketStatusEnum status;
    private final String description;
    private final DateTime tsDate;
    private final Integer idTicket;

    public TicketStatus(Integer id,TicketStatusEnum status, String description, DateTime tsDate, int idTicket) {
        this.id = id;
        this.status = status;
        this.description = description;
        this.tsDate = tsDate;
        this.idTicket = idTicket;
    }

    public final Integer getId() {
        return id;
    }

    public final TicketStatusEnum getStatus() {
        return status;
    }

    public final String getDescription() {
        return description;
    }

    public final DateTime getTsDate() {
        return tsDate;
    }

    public final int getIdTicket() {
        return idTicket;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        if(id!=null) {
            jsonObject.put("id", id);
        }
        if(status !=null) {
            jsonObject.put("status", status.toJSON());
        }
        if(description !=null) {
            jsonObject.put("description", description);
        }
        if(tsDate != null) {
            jsonObject.put("tsDate", tsDate.toJSON());
        }
        if(idTicket != null) {
            jsonObject.put("idTicket", idTicket);
        }
        return jsonObject;
    }

    public static TicketStatus fromJSON(JSONObject jsonObject) {
        int id = jsonObject.getInt("id");
        TicketStatusEnum status = TicketStatusEnum.fromJSON(jsonObject.getJSONObject("status"));
        String description = null;
        if(jsonObject.has("description"))
            description = jsonObject.getString("description");
        DateTime tsDate = DateTime.fromJSON(jsonObject.getJSONObject("tsDate"));
        int idTicket = jsonObject.getInt("idTicket");

        return new TicketStatus(id, status,description,tsDate, idTicket);
    }

}
