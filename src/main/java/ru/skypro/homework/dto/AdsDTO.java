package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import ru.skypro.homework.model.Ad;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Ads
 */

public class AdsDTO {
    @JsonProperty("count")
    private Integer count;

    @JsonProperty("results")
    @Valid
    private List<AdDTO> results = null;

    public AdsDTO count(Integer count) {
        this.count = count;
        return this;
    }

    /**
     * общее количество объявлений
     *
     * @return count
     */
    @ApiModelProperty(value = "общее количество объявлений")


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public AdsDTO results(List<AdDTO> results) {
        this.results = results;
        return this;
    }

    public AdsDTO addResultsItem(AdDTO resultsItem) {
        if (this.results == null) {
            this.results = new ArrayList<>();
        }
        this.results.add(resultsItem);
        return this;
    }

    /**
     * Get results
     *
     * @return results
     */

    @ApiModelProperty


    @Valid

    public List<AdDTO> getResults() {
        return results;
    }

    public void setResults(List<AdDTO> results) {
        this.results = results;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AdsDTO adsDTO = (AdsDTO) o;
        return Objects.equals(this.count, adsDTO.count) &&
                Objects.equals(this.results, adsDTO.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, results);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Ads {\n");

        sb.append("    count: ").append(toIndentedString(count)).append("\n");
        sb.append("    results: ").append(toIndentedString(results)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

