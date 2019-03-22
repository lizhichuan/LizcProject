package com.lizc.app.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DocumentBeanVO {

    /**
     * info : {"_postman_id":"94078337-7431-c0b0-b073-290938b2272e","name":"集币头条","schema":"https://schema.getpostman.com/json/collection/v2.0.0/collection.json"}
     * item : [{"name":"1、启动","item":[{"name":"1.1、获取启动广告列表","request":{"method":"POST","header":[{"key":"appcode","value":"CollectCurrency"},{"key":"appversion","value":"1.0"},{"key":"languagetype","value":"zh-cn"},{"key":"devicetype","value":"ios"},{"key":"devicemodel","value":"phone"},{"key":"sys","value":"iphone5s"},{"key":"sysversion","value":"7.1.2"},{"key":"deviceidentifier","value":"deviceidentifier"},{"key":"service","value":"/http/banner"},{"key":"action","value":"getStartupBannerList.action"},{"key":"page","value":"1"},{"key":"pagesize","value":"10"},{"key":"sign","value":"7c8bc65fce87b59b6c9b060c46631f2d"},{"key":"noncestr","value":"noncestr"},{"key":"timestamp","value":"1475729371846"}],"body":{},"url":{"raw":"http://app.wenjs.cn/CollectCurrency-api/http/accessPortal/accessPortal.action?debug=true","protocol":"http","host":["app","wenjs","cn"],"path":["CollectCurrency-api","http","accessPortal","accessPortal.action"],"query":[{"key":"debug","value":"true"}]},"description":"  出参\n  id;//广告id\n  image;//广告图片\n  linkurl;//广告链接\n  "},"response":[]}]}]
     */

    private InfoBean info;
    private java.util.List<ItemBeanX> item;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public List<ItemBeanX> getItem() {
        if (item == null) {
            return new ArrayList<>();
        }
        return item;
    }

    public void setItem(List<ItemBeanX> item) {
        this.item = item;
    }

    public static class InfoBean implements Serializable {
        /**
         * _postman_id : 94078337-7431-c0b0-b073-290938b2272e
         * name : 集币头条
         * schema : https://schema.getpostman.com/json/collection/v2.0.0/collection.json
         */

        private String _postman_id;
        private String name;
        private String schema;

        public String get_postman_id() {
            return _postman_id;
        }

        public void set_postman_id(String _postman_id) {
            this._postman_id = _postman_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSchema() {
            return schema;
        }

        public void setSchema(String schema) {
            this.schema = schema;
        }
    }

    public static class ItemBeanX implements Serializable {


        /**
         * name : 1、启动
         * item : [{"name":"1.1、获取启动广告列表","request":{"method":"POST","header":[{"key":"appcode","value":"CollectCurrency"},{"key":"appversion","value":"1.0"},{"key":"languagetype","value":"zh-cn"},{"key":"devicetype","value":"ios"},{"key":"devicemodel","value":"phone"},{"key":"sys","value":"iphone5s"},{"key":"sysversion","value":"7.1.2"},{"key":"deviceidentifier","value":"deviceidentifier"},{"key":"service","value":"/http/banner"},{"key":"action","value":"getStartupBannerList.action"},{"key":"page","value":"1"},{"key":"pagesize","value":"10"},{"key":"sign","value":"7c8bc65fce87b59b6c9b060c46631f2d"},{"key":"noncestr","value":"noncestr"},{"key":"timestamp","value":"1475729371846"}],"body":{},"url":{"raw":"http://app.wenjs.cn/CollectCurrency-api/http/accessPortal/accessPortal.action?debug=true","protocol":"http","host":["app","wenjs","cn"],"path":["CollectCurrency-api","http","accessPortal","accessPortal.action"],"query":[{"key":"debug","value":"true"}]},"description":"  出参\n  id;//广告id\n  image;//广告图片\n  linkurl;//广告链接\n  "},"response":[]}]
         */

        private String name;
        private java.util.List<ItemBean> item;

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ItemBean> getItem() {
            if (item == null) {
                return new ArrayList<>();
            }
            return item;
        }

        public void setItem(List<ItemBean> item) {
            this.item = item;
        }

        public static class ItemBean implements Serializable {

            /**
             * name : 1.1、获取启动广告列表
             * request : {"method":"POST","header":[{"key":"appcode","value":"CollectCurrency"},{"key":"appversion","value":"1.0"},{"key":"languagetype","value":"zh-cn"},{"key":"devicetype","value":"ios"},{"key":"devicemodel","value":"phone"},{"key":"sys","value":"iphone5s"},{"key":"sysversion","value":"7.1.2"},{"key":"deviceidentifier","value":"deviceidentifier"},{"key":"service","value":"/http/banner"},{"key":"action","value":"getStartupBannerList.action"},{"key":"page","value":"1"},{"key":"pagesize","value":"10"},{"key":"sign","value":"7c8bc65fce87b59b6c9b060c46631f2d"},{"key":"noncestr","value":"noncestr"},{"key":"timestamp","value":"1475729371846"}],"body":{},"url":{"raw":"http://app.wenjs.cn/CollectCurrency-api/http/accessPortal/accessPortal.action?debug=true","protocol":"http","host":["app","wenjs","cn"],"path":["CollectCurrency-api","http","accessPortal","accessPortal.action"],"query":[{"key":"debug","value":"true"}]},"description":"  出参\n  id;//广告id\n  image;//广告图片\n  linkurl;//广告链接\n  "}
             * response : []
             */

            private String name;
            private RequestBean request;
            private java.util.List<?> response;

            public String getName() {
                return name == null ? "" : name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public RequestBean getRequest() {
                return request;
            }

            public void setRequest(RequestBean request) {
                this.request = request;
            }

            public List<?> getResponse() {
                if (response == null) {
                    return new ArrayList<>();
                }
                return response;
            }

            public void setResponse(List<?> response) {
                this.response = response;
            }

            public static class RequestBean implements Serializable {

                /**
                 * method : POST
                 * header : [{"key":"appcode","value":"CollectCurrency"},{"key":"appversion","value":"1.0"},{"key":"languagetype","value":"zh-cn"},{"key":"devicetype","value":"ios"},{"key":"devicemodel","value":"phone"},{"key":"sys","value":"iphone5s"},{"key":"sysversion","value":"7.1.2"},{"key":"deviceidentifier","value":"deviceidentifier"},{"key":"service","value":"/http/banner"},{"key":"action","value":"getStartupBannerList.action"},{"key":"page","value":"1"},{"key":"pagesize","value":"10"},{"key":"sign","value":"7c8bc65fce87b59b6c9b060c46631f2d"},{"key":"noncestr","value":"noncestr"},{"key":"timestamp","value":"1475729371846"}]
                 * body : {}
                 * url : {"raw":"http://app.wenjs.cn/CollectCurrency-api/http/accessPortal/accessPortal.action?debug=true","protocol":"http","host":["app","wenjs","cn"],"path":["CollectCurrency-api","http","accessPortal","accessPortal.action"],"query":[{"key":"debug","value":"true"}]}
                 * description :   出参
                 * id;//广告id
                 * image;//广告图片
                 * linkurl;//广告链接
                 */

                private String method;
                private BodyBean body;
                private UrlBean url;
                private String description;
                private List<HeaderBean> header;

                public String getMethod() {
                    return method;
                }

                public void setMethod(String method) {
                    this.method = method;
                }

                public BodyBean getBody() {
                    return body;
                }

                public void setBody(BodyBean body) {
                    this.body = body;
                }

                public UrlBean getUrl() {
                    return url;
                }

                public void setUrl(UrlBean url) {
                    this.url = url;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public List<HeaderBean> getHeader() {
                    return header;
                }

                public void setHeader(List<HeaderBean> header) {
                    this.header = header;
                }

                public static class BodyBean implements Serializable {

                    /**
                     * mode : formdata
                     * formdata : [{"key":"image","type":"file","src":""},{"key":"sources","value":"123","type":"text"}]
                     */

                    private String mode;
                    private List<FormdataBean> formdata;
                    private List<UrlEncodedBean> urlencoded;

                    public List<UrlEncodedBean> getUrlencoded() {
                        if (urlencoded == null) {
                            return new ArrayList<>();
                        }
                        return urlencoded;
                    }

                    public void setUrlencoded(List<UrlEncodedBean> urlencoded) {
                        this.urlencoded = urlencoded;
                    }

                    public String getMode() {
                        return mode;
                    }

                    public void setMode(String mode) {
                        this.mode = mode;
                    }

                    public List<FormdataBean> getFormdata() {
                        return formdata;
                    }

                    public void setFormdata(List<FormdataBean> formdata) {
                        this.formdata = formdata;
                    }

                    public static class UrlEncodedBean implements Serializable {

                        /**
                         * key : informationid
                         * value : jt_information_zx01_t01_181229234311002395
                         * type : text
                         */

                        private String key;
                        private String value;
                        private String type;

                        public String getKey() {
                            return key;
                        }

                        public void setKey(String key) {
                            this.key = key;
                        }

                        public String getValue() {
                            return value;
                        }

                        public void setValue(String value) {
                            this.value = value;
                        }

                        public String getType() {
                            return type;
                        }

                        public void setType(String type) {
                            this.type = type;
                        }
                    }

                    public static class FormdataBean implements Serializable {
                        /**
                         * key : image
                         * type : file
                         * src :
                         * value : 123
                         */

                        private String key;
                        private String type;
                        private String src;
                        private String value;

                        public String getKey() {
                            return key;
                        }

                        public void setKey(String key) {
                            this.key = key;
                        }

                        public String getType() {
                            return type;
                        }

                        public void setType(String type) {
                            this.type = type;
                        }

                        public String getSrc() {
                            return src;
                        }

                        public void setSrc(String src) {
                            this.src = src;
                        }

                        public String getValue() {
                            return value;
                        }

                        public void setValue(String value) {
                            this.value = value;
                        }
                    }
                }

                public static class UrlBean implements Serializable {
                    /**
                     * raw : http://app.wenjs.cn/CollectCurrency-api/http/accessPortal/accessPortal.action?debug=true
                     * protocol : http
                     * host : ["app","wenjs","cn"]
                     * path : ["CollectCurrency-api","http","accessPortal","accessPortal.action"]
                     * query : [{"key":"debug","value":"true"}]
                     */

                    private String raw;
                    private String protocol;
                    private List<String> host;
                    private List<String> path;
                    private List<QueryBean> query;

                    public String getRaw() {
                        return raw;
                    }

                    public void setRaw(String raw) {
                        this.raw = raw;
                    }

                    public String getProtocol() {
                        return protocol;
                    }

                    public void setProtocol(String protocol) {
                        this.protocol = protocol;
                    }

                    public List<String> getHost() {
                        return host;
                    }

                    public void setHost(List<String> host) {
                        this.host = host;
                    }

                    public List<String> getPath() {
                        return path;
                    }

                    public void setPath(List<String> path) {
                        this.path = path;
                    }

                    public List<QueryBean> getQuery() {
                        return query;
                    }

                    public void setQuery(List<QueryBean> query) {
                        this.query = query;
                    }

                    public static class QueryBean implements Serializable {
                        /**
                         * key : debug
                         * value : true
                         */

                        private String key;
                        private String value;

                        public String getKey() {
                            return key;
                        }

                        public void setKey(String key) {
                            this.key = key;
                        }

                        public String getValue() {
                            return value;
                        }

                        public void setValue(String value) {
                            this.value = value;
                        }
                    }
                }

                public static class HeaderBean implements Serializable {
                    /**
                     * key : appcode
                     * value : CollectCurrency
                     */

                    private String key;
                    private String value;

                    public String getKey() {
                        return key;
                    }

                    public void setKey(String key) {
                        this.key = key;
                    }

                    public String getValue() {
                        return value;
                    }

                    public void setValue(String value) {
                        this.value = value;
                    }
                }
            }
        }
    }


}
