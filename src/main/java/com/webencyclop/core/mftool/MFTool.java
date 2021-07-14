package com.webencyclop.core.mftool;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webencyclop.core.mftool.models.input.Scheme;
import com.webencyclop.core.mftool.models.output.SchemeNameCodePair;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MFTool {

    public static final String BASE_URL = "https://api.mfapi.in";
    private final OkHttpClient client = new OkHttpClient();
    private final List<SchemeNameCodePair> schemeNameCodePairList = new ArrayList<>();
    ObjectMapper mapper = new ObjectMapper();

    public List<SchemeNameCodePair> matchingSchemeName(String searchTerm) throws IOException {
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
