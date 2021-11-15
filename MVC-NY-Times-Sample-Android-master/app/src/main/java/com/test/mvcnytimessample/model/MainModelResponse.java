package com.test.mvcnytimessample.model;

import java.util.List;

public class MainModelResponse {
    private String copyright;
    private List<ResultsItem> results;
    private int numResults;
    private String status;

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public List<ResultsItem> getResults() {
        return results;
    }

    public void setResults(List<ResultsItem> results) {
        this.results = results;
    }

    public int getNumResults() {
        return numResults;
    }

    public void setNumResults(int numResults) {
        this.numResults = numResults;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return
                "MainModelResponse{" +
                        "copyright = '" + copyright + '\'' +
                        ",results = '" + results + '\'' +
                        ",num_results = '" + numResults + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }

    // MediaItem model for response
    public class MediaItem {
        private String copyright;
        private List<MediaMetadataItem> mediaMetadata;
        private String subtype;
        private String caption;
        private String type;
        private int approvedForSyndication;

        public String getCopyright() {
            return copyright;
        }

        public void setCopyright(String copyright) {
            this.copyright = copyright;
        }

        public List<MediaMetadataItem> getMediaMetadata() {
            return mediaMetadata;
        }

        public void setMediaMetadata(List<MediaMetadataItem> mediaMetadata) {
            this.mediaMetadata = mediaMetadata;
        }

        public String getSubtype() {
            return subtype;
        }

        public void setSubtype(String subtype) {
            this.subtype = subtype;
        }

        public String getCaption() {
            return caption;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getApprovedForSyndication() {
            return approvedForSyndication;
        }

        public void setApprovedForSyndication(int approvedForSyndication) {
            this.approvedForSyndication = approvedForSyndication;
        }

        @Override
        public String toString() {
            return
                    "MediaItem{" +
                            "copyright = '" + copyright + '\'' +
                            ",media-metadata = '" + mediaMetadata + '\'' +
                            ",subtype = '" + subtype + '\'' +
                            ",caption = '" + caption + '\'' +
                            ",type = '" + type + '\'' +
                            ",approved_for_syndication = '" + approvedForSyndication + '\'' +
                            "}";
        }
    }


    // MediaMetadataItem model for response
    public class MediaMetadataItem {
        private String format;
        private int width;
        private String url;
        private int height;

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        @Override
        public String toString() {
            return
                    "MediaMetadataItem{" +
                            "format = '" + format + '\'' +
                            ",width = '" + width + '\'' +
                            ",url = '" + url + '\'' +
                            ",height = '" + height + '\'' +
                            "}";
        }
    }

    //ResultsItem model for response
    public class ResultsItem{
        private List<String> perFacet;
        private String orgFacet;
        private Object column;
        private String section;
        private String jsonMemberAbstract;
        private String source;
        private long assetId;
        private List<MediaItem> media;
        private String type;
        private String title;
        private List<String> desFacet;
        private String url;
        private String adxKeywords;
        private String geoFacet;
        private long id;
        private String byline;
        private String publishedDate;
        private int views;

        public void setPerFacet(List<String> perFacet){
            this.perFacet = perFacet;
        }

        public List<String> getPerFacet(){
            return perFacet;
        }

        public void setOrgFacet(String orgFacet){
            this.orgFacet = orgFacet;
        }

        public String getOrgFacet(){
            return orgFacet;
        }

        public void setColumn(Object column){
            this.column = column;
        }

        public Object getColumn(){
            return column;
        }

        public void setSection(String section){
            this.section = section;
        }

        public String getSection(){
            return section;
        }

        public void setJsonMemberAbstract(String jsonMemberAbstract){
            this.jsonMemberAbstract = jsonMemberAbstract;
        }

        public String getJsonMemberAbstract(){
            return jsonMemberAbstract;
        }

        public void setSource(String source){
            this.source = source;
        }

        public String getSource(){
            return source;
        }

        public void setAssetId(long assetId){
            this.assetId = assetId;
        }

        public long getAssetId(){
            return assetId;
        }

        public void setMedia(List<MediaItem> media){
            this.media = media;
        }

        public List<MediaItem> getMedia(){
            return media;
        }

        public void setType(String type){
            this.type = type;
        }

        public String getType(){
            return type;
        }

        public void setTitle(String title){
            this.title = title;
        }

        public String getTitle(){
            return title;
        }

        public void setDesFacet(List<String> desFacet){
            this.desFacet = desFacet;
        }

        public List<String> getDesFacet(){
            return desFacet;
        }

        public void setUrl(String url){
            this.url = url;
        }

        public String getUrl(){
            return url;
        }

        public void setAdxKeywords(String adxKeywords){
            this.adxKeywords = adxKeywords;
        }

        public String getAdxKeywords(){
            return adxKeywords;
        }

        public void setGeoFacet(String geoFacet){
            this.geoFacet = geoFacet;
        }

        public String getGeoFacet(){
            return geoFacet;
        }

        public void setId(long id){
            this.id = id;
        }

        public long getId(){
            return id;
        }

        public void setByline(String byline){
            this.byline = byline;
        }

        public String getByline(){
            return byline;
        }

        public void setPublishedDate(String publishedDate){
            this.publishedDate = publishedDate;
        }

        public String getPublishedDate(){
            return publishedDate;
        }

        public void setViews(int views){
            this.views = views;
        }

        public int getViews(){
            return views;
        }

        @Override
        public String toString(){
            return
                    "ResultsItem{" +
                            "per_facet = '" + perFacet + '\'' +
                            ",org_facet = '" + orgFacet + '\'' +
                            ",column = '" + column + '\'' +
                            ",section = '" + section + '\'' +
                            ",abstract = '" + jsonMemberAbstract + '\'' +
                            ",source = '" + source + '\'' +
                            ",asset_id = '" + assetId + '\'' +
                            ",media = '" + media + '\'' +
                            ",type = '" + type + '\'' +
                            ",title = '" + title + '\'' +
                            ",des_facet = '" + desFacet + '\'' +
                            ",url = '" + url + '\'' +
                            ",adx_keywords = '" + adxKeywords + '\'' +
                            ",geo_facet = '" + geoFacet + '\'' +
                            ",id = '" + id + '\'' +
                            ",byline = '" + byline + '\'' +
                            ",published_date = '" + publishedDate + '\'' +
                            ",views = '" + views + '\'' +
                            "}";
        }
    }
}