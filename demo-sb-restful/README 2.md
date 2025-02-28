# @ComponentScan
- Try to scan all classes with @Controller, @Service, @Repository, @Configuration, @Component
- Try to find out their relationship
  - For example, CatController @Autowired CatService. -> CatService bean must be ready for CatController bean creation. If CatService bean not ready, CatController bean creation fail, server start fail. 