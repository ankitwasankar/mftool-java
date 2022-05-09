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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class MFTool {

    public static final String BASE_URL = "https://api.mfapi.in";
    private final OkHttpClient client = new OkHttpClient();
    private final List<SchemeNameCodePair> schemeNameCodePairList = new ArrayList<>();
    private final Map<String, SchemeDetails> schemeDetailMap = new HashMap<>();
    private final Map<String, List<Data>> schemeNavMap = new HashMap<>();
    ObjectMapper mapper = new ObjectMapper();
    LocalDate dataUpdateDate;
    Map<String, LocalDate> navUpdatedMap = new HashMap<>();

    public List<SchemeNameCodePair> matchingScheme(String searchTerm) throws IOException {
        if (schemeNameCodePairList.isEmpty() || LocalDate.now().compareTo(dataUpdateDate) != 0) {
            dataUpdateDate = LocalDate.now();
            updateSchemeNameCodePairList();
        }
        return schemeNameCodePairList
                .stream()
                .filter(obj -> obj.getName().contains(searchTerm))
                .sorted()
                .collect(Collectors.toList());
    }


    public List<SchemeNameCodePair> allSchemes() throws IOException {
        if (schemeNameCodePairList.isEmpty() || LocalDate.now().compareTo(dataUpdateDate) != 0) {
            dataUpdateDate = LocalDate.now();
            updateSchemeNameCodePairList();
        }
        return schemeNameCodePairList
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public SchemeDetails schemeDetails(String code) throws IOException {
        if (isSchemeDetailNeedToUpdate(code)) {
            updateSchemeDetails(code);
        }
        return schemeDetailMap.get(code);
    }

    public List<Data> historicNavForScheme(String code) throws IOException {
        if (isSchemeDetailNeedToUpdate(code)) {
            updateSchemeDetails(code);
        }
        return schemeNavMap.get(code);
    }

    public BigDecimal getCurrentNav(String code) throws IOException {
        if (isSchemeDetailNeedToUpdate(code)) {
            updateSchemeDetails(code);
        }
        return schemeNavMap.get(code).get(0).getNav();
    }

    public BigDecimal getNavFor(String code, LocalDate date) throws IOException {
        if (isSchemeDetailNeedToUpdate(code)) {
            updateSchemeDetails(code);
        }
        List<Data> d = schemeNavMap.get(code)
                .stream()
                .filter(data -> compareDate(date, data.getDate()))
                .sorted()
                .collect(Collectors.toList());
        if (!d.isEmpty()) {
            return d.get(0).getNav();
        }
        return new BigDecimal("0");

    }

    private void updateSchemeDetails(String code) throws IOException {
        var request = new Request.Builder()
                .url(BASE_URL + "/mf/" + code)
                .build();
        var response = client.newCall(request).execute();
        var schemeDetails = mapper.readValue(Objects.requireNonNull(response.body()).string(), InputSchemeDetails.class);
        navUpdatedMap.put(code, LocalDate.now());
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

    private boolean compareDate(LocalDate date1, LocalDate date2) {
        return date1.equals(date2) || date1.isBefore(date2);
    }

    private boolean isSchemeDetailNeedToUpdate(String code) {
        return !schemeDetailMap.containsKey(code) || (navUpdatedMap.get(code) != null && LocalDate.now().compareTo(navUpdatedMap.get(code)) != 0);
    }
}
