import request from '@/utils/request';
// list
export const baomingcansaiList = (params) => {
    return request({
        url: '/baomingcansai/page',
        method: 'post',
        data: params,
    })
};
export const baomingcansaiDetail = (id) => {
    return request({
        url: '/baomingcansai/'+id,
        method: 'get',
    })
};
// add
export const baomingcansaiSave = (params) => {
    return request({
        url: '/baomingcansai',
        method: 'post',
        data: params,
    })
};
// updt
export const baomingcansaiEdit = (params) => {
    return request({
        url: '/baomingcansai',
        method: 'put',
        data: params,
    })
};
// delete
export const baomingcansaiDelete = (id) => {
    return request({
        url: '/baomingcansai/'+id,
        method: 'delete',
    })
};
// pldel
export const baomingcansaiDeleteList = (baomingcansais) => {
    return request({
        url: '/baomingcansai/deleteList',
        data:{list:baomingcansais},
        method: 'post',
    })
};
// all
export const baomingcansaiAllList = () => {
    return request({
        url: '/baomingcansai',
        method: 'get',
    })
};

