package com.binaracademy.binarfud.service;

import com.binaracademy.binarfud.model.Merchant;
import com.binaracademy.binarfud.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Objects;
import java.util.Optional;

public class MerchantServiceImpl implements MerchantService{
    @Autowired
    private MerchantRepository merchantRepository;

    public Boolean addNewMerchant(Merchant merchant) {
        return Optional.ofNullable(merchant)
                .map(newMerchant -> merchantRepository.save(merchant))
                .map(Objects::nonNull)
                .orElse(false);
    }

    public Boolean updateStatusMerchant(String merchantName, Merchant merchant) {
        return Optional.ofNullable(merchantName)
                .map(merchantRepository::findByMerchantName)
                .map(merchant1 -> {
                    merchant1.setOpen(merchant.getOpen());
                    return merchantRepository.save(merchant1);
                })
                .map(Objects::nonNull)
                .orElse(false);
    }

    public Page<Merchant> getAllOpenMerchant(Boolean open, Pageable pageable) {
        return merchantRepository.findByOpen(open, pageable);
    }
}
