Message Conversion
    工作方式：
        假设请求路径http://localhost:8080/rest/xmlquery
        控制器处理方法： @RequestMapping(value = "/xmlquery")
                    public @ResponseBody List<Product> xmlquery(Model model){
                        return service.queryall();
                    }
        1.判断控制器处理方法中是否存在@ResponseBody
            a.不存在，需要返回逻辑视图名称，工作路径如Content Negotiation
            b.存在，不需要返回逻辑视图名称
        2.判断@RequestMapping注解中是否存在produces属性
            a.存在.则以该属性值指定的数据格式返回
            b.不存在.搜索请求报文的请求头的Accept属性
        3.Accept属性在未被修改的情况下  Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8
            a.Accept属性被修改，如 request.setHeader("Accept","application/json");
                则按照指定的数据格式返回
            b.Accept属性未被修改
        4.先以html的格式查找解析器，如果匹配则以html的数据格式返回
        5.以xml的格式查找转换器，如果匹配则以xml的数据格式返回，
            如果找不到会报错，不会在以json的格式进行查找，因为默认情况下
            Accept属性根本就不接收json格式Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8
  
  @RestController慎用
        该注解能使控制器类的所有方法都附上@Response注解
        但这会使得控制器类中返回逻辑视图名称的方法（即使用视图解析器
        而不是消息转换器的方法）失效
        
  @RequestBody
        该注解能将请求过来的数据转换为对象
        @RequestMapping中存在comsumes属性值，那只能转换该属性值所指定的数据格式
  
  异常处理
        1.创建异常消息类Error
        2.创建异常类ProductNotFoundException
        3.创建异常处理器
             //异常处理器，处理该类中所有抛出ProductNotFoundException
                @ExceptionHandler(ProductNotFoundException.class)
                @ResponseStatus(HttpStatus.NOT_FOUND)
                public @ResponseBody Error productNotFound(ProductNotFoundException e){
                    long productId=e.getProductId();
                    return new Error(404,"Product ["+productId+"] not found");
                }
                
  使用ResponseEntity类设置响应头
        @RequestMapping(value = "/save",consumes = "application/json",produces = "application/json")
            public @ResponseBody ResponseEntity<Product> saveProduct(
                    Model model,@RequestBody Product product,
                    UriComponentsBuilder ucb){
                Product product1=product;
                service.save(product1);
                HttpHeaders headers=new HttpHeaders();
                URI locationUri=ucb.path("/rest/query/")
                                    .path(String.valueOf(product1.getId()))
                                    .build()
                                    .toUri();
                headers.setLocation(locationUri);
                ResponseEntity<Product> responseEntity=
                        new ResponseEntity<>(product1,headers,HttpStatus.CREATED);
                return responseEntity;
            }
       注解@ResponseBody对应produces属性，检索请求头的Accept属性（指定接受的返回的数据格式）
            以指定的数据格式返回数据,如果POJO添加@ResponseBody注解，不接收text/plain格式的请求参数
            可以接受application/json格式的请求参数，如果String类型数据添加@ResponseBody注解，
            接收text/plain格式的请求参数
       注解@RequestBody对应consumes属性，检索请求体的Content-Type属性（指定请求参数的数据类型）
            以指定的数据格式接收请求的参数
       前端可以通过ajax发送json类型的请求参数，见welcome.html
      
  UriComponentsBuilder ucb 可以不用考虑根路径设置请求路径
  ResponseEntity可以在返回数据的同时设置请求头。
      
 