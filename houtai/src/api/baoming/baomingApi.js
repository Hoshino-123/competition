import request from '@/utils/request';
// list
export const baomingList = (params) => {
    return request({
        url: '/baoming/page',
        method: 'post',
        data: params,
    })
};
export const baomingDetail = (id) => {
    return request({
        url: '/baoming/'+id,
        method: 'get',
    })
};
// add
export const baomingSave = (params) => {
    return request({
        url: '/baoming',
        method: 'post',
        data: params,
    })
};
// updt
export const baomingEdit = (params) => {
    return request({
        url: '/baoming',
        method: 'put',
        data: params,
    })
};
// delete
export const baomingDelete = (id) => {
    return request({
        url: '/baoming/'+id,
        method: 'delete',
    })
};
// pldel
export const baomingDeleteList = (baomings) => {
    return request({
        url: '/baoming/deleteList',
        data:{list:baomings},
        method: 'post',
    })
};
// all
export const baomingAllList = () => {
    return request({
        url: '/baoming',
        method: 'get',
    })
};

