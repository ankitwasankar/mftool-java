package com.webencyclop.core.mftool;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webencyclop.core.mftool.models.Data;
import com.webencyclop.core.mftool.models.input.InputSchemeDetails;
import com.webencyclop.core.mftool.models.input.Scheme;
import com.webencyclop.core.mftool.models.output.SchemeDetails;
import com.webencyclop.core.mftool.models.output.SchemeNameCodePair;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class MFTool {

    public static final String BASE_URL = "https://api.mfapi.in";
    private final OkHttpClient client = new OkHttpClient();
    private final List<SchemeNameCodePair> schemeNameCodePairList = new ArrayList<>();
    private final Map<String, SchemeDetails> schemeDetailMap = new HashMap<>();
    private final Map<String, List<Data>> schemeNavMap = new HashMap<>();
    ObjectMapper mapper = new ObjectMapper();

    public List<SchemeNameCodePair> matchingScheme(String searchTerm) throws IOException {
        if (schemeNameCodePairList.isEmpty()) {
            updateSchemeNameCodePairList();
        }
        return schemeNameCodePairList
                .stream()
                .filter(obj -> obj.getName().contains(searchTerm))
                .sorted()
                .collect(Collectors.toList());
    }


    public List<SchemeNameCodePair> allSchemes() throws IOException {
        if (schemeNameCodePairList.isEmpty()) {
            updateSchemeNameCodePairList();
        }
        return schemeNameCodePairList
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public SchemeDetails schemeDetails(String code) throws IOException {
        if (!schemeDetailMap.containsKey(code)) {
            updateSchemeDetails(code);
        }
        return schemeDetailMap.get(code);
    }

    public List<Data> historicNavForScheme(String code) throws IOException {
        if (!schemeDetailMap.containsKey(code)) {
            updateSchemeDetails(code);
        }
        return schemeNavMap.get(code);
    }

    private void updateSchemeDetails(String code) throws IOException {
        var request = new Request.Builder()
                .url(BASE_URL + "/mf/" + code)
                .build();
        var response = client.newCall(request).execute();
        var schemeDetails = mapper.readValue(Objects.requireNonNull(response.body()).string(), InputSchemeDetails.class);
        schemeDetailMap.put(code, schemeDetails.mapToSchemeDetail());
        schemeNavMap.put(code, schemeDetails.mapToNav());
    }

    private void updateSchemeNameCodePairList() throws IOException {
        var request = new Request.Builder()
                .url(BASE_URL + "/mf")
                .build();
        var response = client.newCall(request).execute();
        List<Scheme> list = mapper.readValue(Objects.requireNonNull(response.body()).string(),
                mapper.getTypeFactory().constructCollectionType(List.class, Scheme.class));
        schemeNameCodePairList.addAll(list.stream().map(o -> o.map(o)).collect(Collectors.toList()));
    }


}
