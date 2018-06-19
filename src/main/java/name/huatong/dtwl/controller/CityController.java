package name.huatong.dtwl.controller;

import io.swagger.annotations.ApiOperation;
import name.huatong.dtwl.dao.CityDao;
import name.huatong.dtwl.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/citys")
public class CityController {

    @Autowired
    private CityDao cityDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public City save(@RequestBody City city) {
        cityDao.save(city);

        return city;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public City get(@PathVariable Long id) {
        return cityDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public City update(@RequestBody City city) {
        cityDao.update(city);

        return city;
    }

    /*@GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return cityDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<City> list(PageTableRequest request) {
                return cityDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }*/

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        cityDao.delete(id);
    }
}
