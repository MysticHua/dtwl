package name.huatong.dtwl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import name.huatong.dtwl.page.table.PageTableRequest;
import name.huatong.dtwl.page.table.PageTableHandler;
import name.huatong.dtwl.page.table.PageTableResponse;
import name.huatong.dtwl.page.table.PageTableHandler.CountHandler;
import name.huatong.dtwl.page.table.PageTableHandler.ListHandler;
import name.huatong.dtwl.dao.CityDao;
import name.huatong.dtwl.model.City;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/citys")
public class CityController {

    @Autowired
    private CityDao cityDao;

    @PostMapping
    @ApiOperation(value = "����")
    public City save(@RequestBody City city) {
        cityDao.save(city);

        return city;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "����id��ȡ")
    public City get(@PathVariable Long id) {
        return cityDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "�޸�")
    public City update(@RequestBody City city) {
        cityDao.update(city);

        return city;
    }

    /*@GetMapping
    @ApiOperation(value = "�б�")
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
    @ApiOperation(value = "ɾ��")
    public void delete(@PathVariable Long id) {
        cityDao.delete(id);
    }
}
