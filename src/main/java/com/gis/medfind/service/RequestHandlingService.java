package com.gis.medfind.service;

import java.util.List;

import com.gis.medfind.entity.Request;

public interface RequestHandlingService {

    public void newRequest(Request rq);

    public void acceptRequest(Long requestId);

    public void rejectRequest(Long rq);

    public List<Request> getAllRequests();

}
