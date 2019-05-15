package com.hs.accounts.Controller;

import com.hs.accounts.Commons.RestTemplateResponseDTO;
import com.hs.accounts.DTO.VendorDTO;
import com.hs.accounts.Services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vendor")
public class VendorController {

    @Autowired
    VendorService vendorService;

    @RequestMapping(value = "/" ,method = RequestMethod.POST)
    public RestTemplateResponseDTO postVendor(@RequestBody VendorDTO vendorDTO){
        return vendorService.postVendor(vendorDTO);

    }
}
